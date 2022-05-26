import * as Types from '../../../../graphql-generated-types/types';

import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
const defaultOptions = {} as const;
export type GetPoliciesTotalItemsQueryVariables = Types.Exact<{
  input: Types.PoliciesInput;
}>;


export type GetPoliciesTotalItemsQuery = { __typename?: 'Query', policies: { __typename?: 'PolicyConnectionPayload', totalItems: number } };


export const GetPoliciesTotalItemsDocument = gql`
    query getPoliciesTotalItems($input: PoliciesInput!) {
  policies(input: $input) {
    totalItems
  }
}
    `;

/**
 * __useGetPoliciesTotalItemsQuery__
 *
 * To run a query within a React component, call `useGetPoliciesTotalItemsQuery` and pass it any options that fit your needs.
 * When your component renders, `useGetPoliciesTotalItemsQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useGetPoliciesTotalItemsQuery({
 *   variables: {
 *      input: // value for 'input'
 *   },
 * });
 */
export function useGetPoliciesTotalItemsQuery(baseOptions: Apollo.QueryHookOptions<GetPoliciesTotalItemsQuery, GetPoliciesTotalItemsQueryVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useQuery<GetPoliciesTotalItemsQuery, GetPoliciesTotalItemsQueryVariables>(GetPoliciesTotalItemsDocument, options);
      }
export function useGetPoliciesTotalItemsLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<GetPoliciesTotalItemsQuery, GetPoliciesTotalItemsQueryVariables>) {
          const options = {...defaultOptions, ...baseOptions}
          return Apollo.useLazyQuery<GetPoliciesTotalItemsQuery, GetPoliciesTotalItemsQueryVariables>(GetPoliciesTotalItemsDocument, options);
        }
export type GetPoliciesTotalItemsQueryHookResult = ReturnType<typeof useGetPoliciesTotalItemsQuery>;
export type GetPoliciesTotalItemsLazyQueryHookResult = ReturnType<typeof useGetPoliciesTotalItemsLazyQuery>;
export type GetPoliciesTotalItemsQueryResult = Apollo.QueryResult<GetPoliciesTotalItemsQuery, GetPoliciesTotalItemsQueryVariables>;