import * as Types from '../../../../graphql-generated-types/types';

import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
const defaultOptions = {} as const;
export type CreateEmployeePositionMutationVariables = Types.Exact<{
  input: Types.CreateEmployeePositionInput;
}>;


export type CreateEmployeePositionMutation = { __typename?: 'Mutation', createEmployeePosition: { __typename?: 'EmployeePositionPayload', item?: { __typename?: 'EmployeePosition', uuid: any, name?: string | null } | null } };


export const CreateEmployeePositionDocument = gql`
    mutation createEmployeePosition($input: CreateEmployeePositionInput!) {
  createEmployeePosition(input: $input) {
    item {
      uuid
      name
    }
  }
}
    `;
export type CreateEmployeePositionMutationFn = Apollo.MutationFunction<CreateEmployeePositionMutation, CreateEmployeePositionMutationVariables>;

/**
 * __useCreateEmployeePositionMutation__
 *
 * To run a mutation, you first call `useCreateEmployeePositionMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useCreateEmployeePositionMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [createEmployeePositionMutation, { data, loading, error }] = useCreateEmployeePositionMutation({
 *   variables: {
 *      input: // value for 'input'
 *   },
 * });
 */
export function useCreateEmployeePositionMutation(baseOptions?: Apollo.MutationHookOptions<CreateEmployeePositionMutation, CreateEmployeePositionMutationVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useMutation<CreateEmployeePositionMutation, CreateEmployeePositionMutationVariables>(CreateEmployeePositionDocument, options);
      }
export type CreateEmployeePositionMutationHookResult = ReturnType<typeof useCreateEmployeePositionMutation>;
export type CreateEmployeePositionMutationResult = Apollo.MutationResult<CreateEmployeePositionMutation>;
export type CreateEmployeePositionMutationOptions = Apollo.BaseMutationOptions<CreateEmployeePositionMutation, CreateEmployeePositionMutationVariables>;