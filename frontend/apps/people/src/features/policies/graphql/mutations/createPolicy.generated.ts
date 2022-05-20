import * as Types from '../../../../graphql-generated-types/types';

import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
const defaultOptions = {} as const;
export type CreatePolicyForPoliciesListMutationVariables = Types.Exact<{
  input: Types.CreatePolicyInput;
}>;


export type CreatePolicyForPoliciesListMutation = { __typename?: 'Mutation', createPolicy: { __typename?: 'PolicyPayload', policy?: { __typename?: 'Policy', id: any, title: string, publicationDate?: any | null, file: { __typename?: 'Resource', id: any, url: string } } | null } };


export const CreatePolicyForPoliciesListDocument = gql`
    mutation createPolicyForPoliciesList($input: CreatePolicyInput!) {
  createPolicy(input: $input) {
    policy {
      id
      title
      file {
        id
        url
      }
      publicationDate
    }
  }
}
    `;
export type CreatePolicyForPoliciesListMutationFn = Apollo.MutationFunction<CreatePolicyForPoliciesListMutation, CreatePolicyForPoliciesListMutationVariables>;

/**
 * __useCreatePolicyForPoliciesListMutation__
 *
 * To run a mutation, you first call `useCreatePolicyForPoliciesListMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useCreatePolicyForPoliciesListMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [createPolicyForPoliciesListMutation, { data, loading, error }] = useCreatePolicyForPoliciesListMutation({
 *   variables: {
 *      input: // value for 'input'
 *   },
 * });
 */
export function useCreatePolicyForPoliciesListMutation(baseOptions?: Apollo.MutationHookOptions<CreatePolicyForPoliciesListMutation, CreatePolicyForPoliciesListMutationVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useMutation<CreatePolicyForPoliciesListMutation, CreatePolicyForPoliciesListMutationVariables>(CreatePolicyForPoliciesListDocument, options);
      }
export type CreatePolicyForPoliciesListMutationHookResult = ReturnType<typeof useCreatePolicyForPoliciesListMutation>;
export type CreatePolicyForPoliciesListMutationResult = Apollo.MutationResult<CreatePolicyForPoliciesListMutation>;
export type CreatePolicyForPoliciesListMutationOptions = Apollo.BaseMutationOptions<CreatePolicyForPoliciesListMutation, CreatePolicyForPoliciesListMutationVariables>;