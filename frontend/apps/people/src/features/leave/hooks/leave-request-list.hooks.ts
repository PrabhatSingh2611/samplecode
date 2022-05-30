import { useCallback } from 'react';

import { castToArray, useGetUrlSearchParams, useUpdateSearchUrlParam } from 'wdx';

import {
    usePatchLeaveRequestForLeaveRequestActionButtonsMutation,
    PatchLeaveRequestForLeaveRequestActionButtonsMutationResult,
} from 'features/leave/graphql/mutations/patchLeaveRequestForLeaveRequestActionButtons.generated';
import {
    GetLeaveRequestsForRequestsListQueryHookResult,
    LeaveRequestForRequestsListFragment,
    useGetLeaveRequestsForRequestsListQuery,
} from 'features/leave/graphql/queries/getLeaveRequestsForRequestsList.generated';
import { LeaveRequestStatus, PatchLeaveRequestInput } from 'graphql-generated-types/types';

export const useGetLeaveRequestList = ({
    itemsPerPage,
    pageNumber,
}: {
    itemsPerPage?: number;
    pageNumber?: number;
}): [LeaveRequestForRequestsListFragment[], GetLeaveRequestsForRequestsListQueryHookResult] => {
    const leaveRequestListQuery = useGetLeaveRequestsForRequestsListQuery({
        variables: {
            input: {
                pagination: {
                    itemsPerPage,
                    pageNumber,
                },
            },
        },
    });

    const leaveRequestList = castToArray(leaveRequestListQuery.data?.leaveRequests.items);

    return [leaveRequestList, leaveRequestListQuery];
};

type TPatchLeaveRequestInput = Omit<PatchLeaveRequestInput, 'status'> & {
    status: LeaveRequestStatus;
};

export const usePatchLeaveRequestStatus = (): [
    (values: TPatchLeaveRequestInput) => void,
    PatchLeaveRequestForLeaveRequestActionButtonsMutationResult,
] => {
    const [patch, patchLeaveRequestResult] =
        usePatchLeaveRequestForLeaveRequestActionButtonsMutation();

    const patchLeaveRequestStatus = useCallback(
        (input: TPatchLeaveRequestInput): void => {
            patch({
                variables: {
                    input,
                },
                optimisticResponse: {
                    __typename: 'Mutation',
                    patchLeaveRequest: {
                        __typename: 'LeaveRequestPayload',
                        leaveRequest: {
                            __typename: 'LeaveRequest',
                            id: input.id,
                            status: input.status,
                        },
                    },
                },
            });
        },
        [patch],
    );

    return [patchLeaveRequestStatus, patchLeaveRequestResult];
};

const defaultRowsPerPage = 10;
const defaultPage = 0;

enum ERequestsSearchParams {
    RequestsPerPage = 'requestsPerPage',
    CurrentPage = 'currentPage',
}

const REQUESTS_URL_SEARCH_PARAMS_KEYS: ERequestsSearchParams[] = [
    ERequestsSearchParams.CurrentPage,
    ERequestsSearchParams.RequestsPerPage,
];

interface ILeaveRequestSearchParams {
    currentPage: number;
    requestsPerPage: number;
    onSetCurrentPage: (page: number) => void;
    onSetRowsPerPage: (rowsPerPage: number) => void;
}

export const useLeaveRequestSearchParams = (): ILeaveRequestSearchParams => {
    const { currentPage = defaultPage, requestsPerPage = defaultRowsPerPage } =
        useGetUrlSearchParams(REQUESTS_URL_SEARCH_PARAMS_KEYS);

    const updateSearchParam = useUpdateSearchUrlParam();

    const onSetCurrentPage = (page: number): void => {
        updateSearchParam(ERequestsSearchParams.CurrentPage, page);
    };

    const onSetRowsPerPage = (rowsPerPage: number): void => {
        updateSearchParam(ERequestsSearchParams.RequestsPerPage, rowsPerPage);
    };

    return {
        currentPage: +currentPage,
        requestsPerPage: +requestsPerPage,
        onSetCurrentPage,
        onSetRowsPerPage,
    };
};
