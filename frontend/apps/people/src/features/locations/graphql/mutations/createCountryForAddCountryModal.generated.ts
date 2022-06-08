import * as Types from '../../../../graphql-generated-types/types';

import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
const defaultOptions = {} as const;
export type CreateCountryForAddLocationModalMutationVariables = Types.Exact<{
  input: Types.CreateLocationCountryInput;
}>;


export type CreateCountryForAddLocationModalMutation = { __typename?: 'Mutation', createLocationCountry: { __typename?: 'LocationCountryPayload', locationCountry?: { __typename?: 'LocationCountry', id: any, name: string, iconName: string } | null } };


export const CreateCountryForAddLocationModalDocument = gql`
    mutation createCountryForAddLocationModal($input: CreateLocationCountryInput!) {
  createLocationCountry(input: $input) {
    locationCountry {
      id
      name
      iconName
    }
  }
}
    `;
export type CreateCountryForAddLocationModalMutationFn = Apollo.MutationFunction<CreateCountryForAddLocationModalMutation, CreateCountryForAddLocationModalMutationVariables>;

/**
 * __useCreateCountryForAddLocationModalMutation__
 *
 * To run a mutation, you first call `useCreateCountryForAddLocationModalMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useCreateCountryForAddLocationModalMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [createCountryForAddLocationModalMutation, { data, loading, error }] = useCreateCountryForAddLocationModalMutation({
 *   variables: {
 *      input: // value for 'input'
 *   },
 * });
 */
export function useCreateCountryForAddLocationModalMutation(baseOptions?: Apollo.MutationHookOptions<CreateCountryForAddLocationModalMutation, CreateCountryForAddLocationModalMutationVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useMutation<CreateCountryForAddLocationModalMutation, CreateCountryForAddLocationModalMutationVariables>(CreateCountryForAddLocationModalDocument, options);
      }
export type CreateCountryForAddLocationModalMutationHookResult = ReturnType<typeof useCreateCountryForAddLocationModalMutation>;
export type CreateCountryForAddLocationModalMutationResult = Apollo.MutationResult<CreateCountryForAddLocationModalMutation>;
export type CreateCountryForAddLocationModalMutationOptions = Apollo.BaseMutationOptions<CreateCountryForAddLocationModalMutation, CreateCountryForAddLocationModalMutationVariables>;