import * as Types from '../../../../graphql-generated-types/types';

import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
const defaultOptions = {} as const;
export type CreateLeaveRequestForLeaveRequestDrawerContentMutationVariables = Types.Exact<{
  input: Types.CreateLeaveRequestInput;
}>;


export type CreateLeaveRequestForLeaveRequestDrawerContentMutation = { __typename?: 'Mutation', createLeaveRequest: { __typename?: 'LeaveRequestPayload', leaveRequest?: { __typename?: 'LeaveRequest', id: any, numberOfDays: number, requestDate: any, startDate: any, endDate: any, status: Types.LeaveRequestStatus, comment?: string | null, type: { __typename?: 'LeaveType', id: any, name: string } } | null } };


export const CreateLeaveRequestForLeaveRequestDrawerContentDocument = gql`
    mutation createLeaveRequestForLeaveRequestDrawerContent($input: CreateLeaveRequestInput!) {
  createLeaveRequest(input: $input) {
    leaveRequest {
      id
      numberOfDays
      requestDate
      startDate
      endDate
      status
      comment
      type {
        id
        name
      }
    }
  }
}
    `;
export type CreateLeaveRequestForLeaveRequestDrawerContentMutationFn = Apollo.MutationFunction<CreateLeaveRequestForLeaveRequestDrawerContentMutation, CreateLeaveRequestForLeaveRequestDrawerContentMutationVariables>;

/**
 * __useCreateLeaveRequestForLeaveRequestDrawerContentMutation__
 *
 * To run a mutation, you first call `useCreateLeaveRequestForLeaveRequestDrawerContentMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useCreateLeaveRequestForLeaveRequestDrawerContentMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [createLeaveRequestForLeaveRequestDrawerContentMutation, { data, loading, error }] = useCreateLeaveRequestForLeaveRequestDrawerContentMutation({
 *   variables: {
 *      input: // value for 'input'
 *   },
 * });
 */
export function useCreateLeaveRequestForLeaveRequestDrawerContentMutation(baseOptions?: Apollo.MutationHookOptions<CreateLeaveRequestForLeaveRequestDrawerContentMutation, CreateLeaveRequestForLeaveRequestDrawerContentMutationVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useMutation<CreateLeaveRequestForLeaveRequestDrawerContentMutation, CreateLeaveRequestForLeaveRequestDrawerContentMutationVariables>(CreateLeaveRequestForLeaveRequestDrawerContentDocument, options);
      }
export type CreateLeaveRequestForLeaveRequestDrawerContentMutationHookResult = ReturnType<typeof useCreateLeaveRequestForLeaveRequestDrawerContentMutation>;
export type CreateLeaveRequestForLeaveRequestDrawerContentMutationResult = Apollo.MutationResult<CreateLeaveRequestForLeaveRequestDrawerContentMutation>;
export type CreateLeaveRequestForLeaveRequestDrawerContentMutationOptions = Apollo.BaseMutationOptions<CreateLeaveRequestForLeaveRequestDrawerContentMutation, CreateLeaveRequestForLeaveRequestDrawerContentMutationVariables>;