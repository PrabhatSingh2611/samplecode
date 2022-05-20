import { useCallback, useState } from 'react';

import { makeVar, useReactiveVar } from '@apollo/client';

import { IAddPolicyFormValues } from 'features/policies/components/add-policy-form/AddPolicyForm';
import { useCreatePolicyForPoliciesListMutation } from 'features/policies/graphql/mutations/createPolicy.generated';
import { PolicyStatus } from 'graphql-generated-types/types';
import { useUploadResource } from 'hooks/graphql/upload.hooks';

const isAddPolicyDrawerOpenedVar = makeVar(false);

export const useIsAddPolicyDrawerOpened = (): [boolean, (value: boolean) => void] => {
    const value = useReactiveVar(isAddPolicyDrawerOpenedVar);

    return [value, isAddPolicyDrawerOpenedVar];
};

export const useCreatePolicy = (): [
    create: (params: IAddPolicyFormValues) => void,
    details: { isLoading: boolean },
] => {
    const [createPolicy] = useCreatePolicyForPoliciesListMutation();
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
