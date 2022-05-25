import { useCallback, useState } from 'react';

import { Order } from 'wdx';

import { makeVar, useReactiveVar } from '@apollo/client';

import { IAddPolicyFormValues } from 'features/policies/components/add-policy-form/AddPolicyForm';
import { MIN_COUNT_OF_SYMBOLS_TO_SEARCH } from 'features/policies/constants';
import { useCreatePolicyForPoliciesListMutation } from 'features/policies/graphql/mutations/createPolicy.generated';
import { useDeletePolicyForPoliciesListMutation } from 'features/policies/graphql/mutations/deletePolicyForPoliciesList.generated';
import {
    GetPoliciesForPoliciesListQueryResult,
    useGetPoliciesForPoliciesListQuery,
} from 'features/policies/graphql/queries/getPoliciesForPoliciesList.generated';
import { useGetPoliciesListPageSearchParams } from 'features/policies/hooks/hooks';
import { PolicyConnectionPayload, PolicySort, PolicyStatus } from 'graphql-generated-types/types';
import { useUploadResource } from 'hooks/graphql/upload.hooks';

export const useGetPolicies = (): [
    PolicyConnectionPayload | undefined,
    GetPoliciesForPoliciesListQueryResult,
] => {
    const { currentPage, order, policiesPerPage, searchValue } =
        useGetPoliciesListPageSearchParams();

    const sortOrder =
        order === Order.ASC ? PolicySort.PublicationDateAsc : PolicySort.PublicationDateDesc;

    const query = searchValue.length >= MIN_COUNT_OF_SYMBOLS_TO_SEARCH ? searchValue : undefined;

    const getPoliciesQuery = useGetPoliciesForPoliciesListQuery({
        variables: {
            input: {
                where: {
                    query,
                },
                pagination: { pageNumber: +currentPage, itemsPerPage: +policiesPerPage },
                sort: [sortOrder],
            },
        },
        fetchPolicy: 'cache-and-network',
    });
    const policies = getPoliciesQuery.data?.policies;

    return [policies, getPoliciesQuery];
};

const isAddPolicyDrawerOpenedVar = makeVar(false);

export const useIsAddPolicyDrawerOpened = (): [boolean, (value: boolean) => void] => {
    const value = useReactiveVar(isAddPolicyDrawerOpenedVar);

    return [value, isAddPolicyDrawerOpenedVar];
};

export const useCreatePolicy = (
    onCompleted: () => void,
): [create: (params: IAddPolicyFormValues) => void, details: { isLoading: boolean }] => {
    const [createPolicy] = useCreatePolicyForPoliciesListMutation({ onCompleted });
    const [uploadResource] = useUploadResource();
    const [isLoading, setIsLoading] = useState(false);

    const create = useCallback(
        async ({ title, file }: IAddPolicyFormValues) => {
            setIsLoading(true);

            const uploadResourceResult = await uploadResource(file);
            const resourceId = uploadResourceResult.data?.uploadResource?.resource?.id;

            await createPolicy({
                variables: {
                    input: {
                        title,
                        file: {
                            id: resourceId,
                        },
                        status: PolicyStatus.Published,
                    },
                },
            });

            setIsLoading(false);
        },
        [createPolicy, uploadResource],
    );

    return [create, { isLoading }];
};

export const useDeletePolicies = ({
    onCompleted,
}: {
    onCompleted: () => void;
}): [(ids: string[]) => void, boolean] => {
    const [deleteMutation, { loading }] = useDeletePolicyForPoliciesListMutation({ onCompleted });
    const deletePolicy = useCallback(
        (ids: string[]) => {
            return deleteMutation({
                variables: { input: { policies: { ids } } },
            });
        },
        [deleteMutation],
    );

    return [deletePolicy, loading];
};
