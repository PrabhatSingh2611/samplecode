import * as Types from '../../../../graphql-generated-types/types';

import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
const defaultOptions = {} as const;
export type CreateLocationForAddLocationModalMutationVariables = Types.Exact<{
  input: Types.CreateLocationInput;
}>;


export type CreateLocationForAddLocationModalMutation = { __typename?: 'Mutation', createLocation: { __typename?: 'LocationPayload', location: { __typename?: 'Location', id: any, name?: string | null, country: { __typename?: 'LocationCountry', id: any } } } };


export const CreateLocationForAddLocationModalDocument = gql`
    mutation createLocationForAddLocationModal($input: CreateLocationInput!) {
  createLocation(input: $input) {
    location {
      id
      name
      country {
        id
      }
    }
  }
}
    `;
export type CreateLocationForAddLocationModalMutationFn = Apollo.MutationFunction<CreateLocationForAddLocationModalMutation, CreateLocationForAddLocationModalMutationVariables>;

/**
 * __useCreateLocationForAddLocationModalMutation__
 *
 * To run a mutation, you first call `useCreateLocationForAddLocationModalMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useCreateLocationForAddLocationModalMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [createLocationForAddLocationModalMutation, { data, loading, error }] = useCreateLocationForAddLocationModalMutation({
 *   variables: {
 *      input: // value for 'input'
 *   },
 * });
 */
export function useCreateLocationForAddLocationModalMutation(baseOptions?: Apollo.MutationHookOptions<CreateLocationForAddLocationModalMutation, CreateLocationForAddLocationModalMutationVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useMutation<CreateLocationForAddLocationModalMutation, CreateLocationForAddLocationModalMutationVariables>(CreateLocationForAddLocationModalDocument, options);
      }
export type CreateLocationForAddLocationModalMutationHookResult = ReturnType<typeof useCreateLocationForAddLocationModalMutation>;
export type CreateLocationForAddLocationModalMutationResult = Apollo.MutationResult<CreateLocationForAddLocationModalMutation>;
export type CreateLocationForAddLocationModalMutationOptions = Apollo.BaseMutationOptions<CreateLocationForAddLocationModalMutation, CreateLocationForAddLocationModalMutationVariables>;