import * as Types from '../../../graphql-generated-types/types';

import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
const defaultOptions = {} as const;
export type UploadResourceMutationVariables = Types.Exact<{
  resource: Types.Scalars['Upload'];
}>;


export type UploadResourceMutation = { __typename?: 'Mutation', uploadResource?: { __typename?: 'ResourcePayload', resource?: { __typename?: 'Resource', id: any, url: string, thumbnail?: string | null } | null } | null };

export type ResourceFragment = { __typename?: 'Resource', id: any, url: string, thumbnail?: string | null };

export const ResourceFragmentDoc = gql`
    fragment Resource on Resource {
  id
  url
  thumbnail
}
    `;
export const UploadResourceDocument = gql`
    mutation uploadResource($resource: Upload!) {
  uploadResource(resource: $resource) {
    resource {
      ...Resource
    }
  }
}
    ${ResourceFragmentDoc}`;
export type UploadResourceMutationFn = Apollo.MutationFunction<UploadResourceMutation, UploadResourceMutationVariables>;

/**
 * __useUploadResourceMutation__
 *
 * To run a mutation, you first call `useUploadResourceMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useUploadResourceMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [uploadResourceMutation, { data, loading, error }] = useUploadResourceMutation({
 *   variables: {
 *      resource: // value for 'resource'
 *   },
 * });
 */
export function useUploadResourceMutation(baseOptions?: Apollo.MutationHookOptions<UploadResourceMutation, UploadResourceMutationVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useMutation<UploadResourceMutation, UploadResourceMutationVariables>(UploadResourceDocument, options);
      }
export type UploadResourceMutationHookResult = ReturnType<typeof useUploadResourceMutation>;
export type UploadResourceMutationResult = Apollo.MutationResult<UploadResourceMutation>;
export type UploadResourceMutationOptions = Apollo.BaseMutationOptions<UploadResourceMutation, UploadResourceMutationVariables>;