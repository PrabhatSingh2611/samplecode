import * as Types from '../../../../graphql-generated-types/types';

import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
const defaultOptions = {} as const;
export type DeletePolicyForPoliciesListMutationVariables = Types.Exact<{
  input: Types.DeletePoliciesInput;
}>;


export type DeletePolicyForPoliciesListMutation = { __typename?: 'Mutation', deletePolicies: { __typename?: 'DeletePoliciesPayload', policies: { __typename?: 'DeletedNodes', ids: Array<any> } } };


export const DeletePolicyForPoliciesListDocument = gql`
    mutation deletePolicyForPoliciesList($input: DeletePoliciesInput!) {
  deletePolicies(input: $input) {
    policies {
      ids
    }
  }
}
    `;
export type DeletePolicyForPoliciesListMutationFn = Apollo.MutationFunction<DeletePolicyForPoliciesListMutation, DeletePolicyForPoliciesListMutationVariables>;

/**
 * __useDeletePolicyForPoliciesListMutation__
 *
 * To run a mutation, you first call `useDeletePolicyForPoliciesListMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useDeletePolicyForPoliciesListMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [deletePolicyForPoliciesListMutation, { data, loading, error }] = useDeletePolicyForPoliciesListMutation({
 *   variables: {
 *      input: // value for 'input'
 *   },
 * });
 */
export function useDeletePolicyForPoliciesListMutation(baseOptions?: Apollo.MutationHookOptions<DeletePolicyForPoliciesListMutation, DeletePolicyForPoliciesListMutationVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useMutation<DeletePolicyForPoliciesListMutation, DeletePolicyForPoliciesListMutationVariables>(DeletePolicyForPoliciesListDocument, options);
      }
export type DeletePolicyForPoliciesListMutationHookResult = ReturnType<typeof useDeletePolicyForPoliciesListMutation>;
export type DeletePolicyForPoliciesListMutationResult = Apollo.MutationResult<DeletePolicyForPoliciesListMutation>;
export type DeletePolicyForPoliciesListMutationOptions = Apollo.BaseMutationOptions<DeletePolicyForPoliciesListMutation, DeletePolicyForPoliciesListMutationVariables>;