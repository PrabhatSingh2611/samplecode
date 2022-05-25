import { useCallback } from 'react';

import {
    CreateLeaveRequestForLeaveRequestDrawerContentMutationResult,
    useCreateLeaveRequestForLeaveRequestDrawerContentMutation,
} from 'features/leave/graphql/mutations/createLeaveRequestForLeaveRequestDrawerContent.generated';
import {
    useGetLeaveTypesForLeaveRequestDrawerContentQuery,
    LeaveTypeForLeaveRequestDrawerContentFragment,
    GetLeaveTypesForLeaveRequestDrawerContentQueryResult,
} from 'features/leave/graphql/queries/leaveTypesForLeaveRequestDrawerContent.generated';
import { CreateLeaveRequestInput, LeaveTypesInput } from 'graphql-generated-types/types';

const leaveTypesPerPage = 100;

export const useGetLeaveTypes = (): [
    LeaveTypeForLeaveRequestDrawerContentFragment[],
    GetLeaveTypesForLeaveRequestDrawerContentQueryResult,
] => {
    const input: LeaveTypesInput = {
        pagination: {
            itemsPerPage: leaveTypesPerPage,
        },
    };

    const leaveTypesQuery = useGetLeaveTypesForLeaveRequestDrawerContentQuery({
        variables: { input },
    });

    const leaveTypes = leaveTypesQuery.data?.leaveTypes.items || [];

    return [leaveTypes, leaveTypesQuery];
};

export const useCreateLeaveRequest = (): [
    (values: CreateLeaveRequestInput) => void,
    CreateLeaveRequestForLeaveRequestDrawerContentMutationResult,
] => {
    const [create, createLeaveRequestResult] =
        useCreateLeaveRequestForLeaveRequestDrawerContentMutation();
    const createLeaveRequest = useCallback(
        (input: CreateLeaveRequestInput): void => {
            create({
                variables: {
                    input,
                },
            });
        },
        [create],
    );

    return [createLeaveRequest, createLeaveRequestResult];
};
