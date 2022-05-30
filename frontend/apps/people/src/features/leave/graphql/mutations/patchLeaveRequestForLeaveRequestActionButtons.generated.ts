import * as Types from '../../../../graphql-generated-types/types';

import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
const defaultOptions = {} as const;
export type PatchLeaveRequestForLeaveRequestActionButtonsMutationVariables = Types.Exact<{
  input: Types.PatchLeaveRequestInput;
}>;


export type PatchLeaveRequestForLeaveRequestActionButtonsMutation = { __typename?: 'Mutation', patchLeaveRequest: { __typename?: 'LeaveRequestPayload', leaveRequest?: { __typename?: 'LeaveRequest', id: any, status: Types.LeaveRequestStatus } | null } };


export const PatchLeaveRequestForLeaveRequestActionButtonsDocument = gql`
    mutation patchLeaveRequestForLeaveRequestActionButtons($input: PatchLeaveRequestInput!) {
  patchLeaveRequest(input: $input) {
    leaveRequest {
      id
      status
    }
  }
}
    `;
export type PatchLeaveRequestForLeaveRequestActionButtonsMutationFn = Apollo.MutationFunction<PatchLeaveRequestForLeaveRequestActionButtonsMutation, PatchLeaveRequestForLeaveRequestActionButtonsMutationVariables>;

/**
 * __usePatchLeaveRequestForLeaveRequestActionButtonsMutation__
 *
 * To run a mutation, you first call `usePatchLeaveRequestForLeaveRequestActionButtonsMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `usePatchLeaveRequestForLeaveRequestActionButtonsMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [patchLeaveRequestForLeaveRequestActionButtonsMutation, { data, loading, error }] = usePatchLeaveRequestForLeaveRequestActionButtonsMutation({
 *   variables: {
 *      input: // value for 'input'
 *   },
 * });
 */
export function usePatchLeaveRequestForLeaveRequestActionButtonsMutation(baseOptions?: Apollo.MutationHookOptions<PatchLeaveRequestForLeaveRequestActionButtonsMutation, PatchLeaveRequestForLeaveRequestActionButtonsMutationVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useMutation<PatchLeaveRequestForLeaveRequestActionButtonsMutation, PatchLeaveRequestForLeaveRequestActionButtonsMutationVariables>(PatchLeaveRequestForLeaveRequestActionButtonsDocument, options);
      }
export type PatchLeaveRequestForLeaveRequestActionButtonsMutationHookResult = ReturnType<typeof usePatchLeaveRequestForLeaveRequestActionButtonsMutation>;
export type PatchLeaveRequestForLeaveRequestActionButtonsMutationResult = Apollo.MutationResult<PatchLeaveRequestForLeaveRequestActionButtonsMutation>;
export type PatchLeaveRequestForLeaveRequestActionButtonsMutationOptions = Apollo.BaseMutationOptions<PatchLeaveRequestForLeaveRequestActionButtonsMutation, PatchLeaveRequestForLeaveRequestActionButtonsMutationVariables>;