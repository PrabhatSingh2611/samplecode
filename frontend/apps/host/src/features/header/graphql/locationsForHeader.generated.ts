import * as Types from '../../../graphql-generated-types/types';

import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
const defaultOptions = {} as const;
export type GetLocationsForHeaderQueryVariables = Types.Exact<{ [key: string]: never; }>;


export type GetLocationsForHeaderQuery = { __typename?: 'Query', locations: { __typename?: 'LocationConnectionPayload', items: Array<{ __typename?: 'Location', id: any, country: string }> } };

export type LocationForHeaderFragment = { __typename?: 'Location', id: any, country: string };

export const LocationForHeaderFragmentDoc = gql`
    fragment LocationForHeader on Location {
  id
  country
}
    `;
export const GetLocationsForHeaderDocument = gql`
    query getLocationsForHeader {
  locations {
    items {
      ...LocationForHeader
    }
  }
}
    ${LocationForHeaderFragmentDoc}`;

/**
 * __useGetLocationsForHeaderQuery__
 *
 * To run a query within a React component, call `useGetLocationsForHeaderQuery` and pass it any options that fit your needs.
 * When your component renders, `useGetLocationsForHeaderQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useGetLocationsForHeaderQuery({
 *   variables: {
 *   },
 * });
 */
export function useGetLocationsForHeaderQuery(baseOptions?: Apollo.QueryHookOptions<GetLocationsForHeaderQuery, GetLocationsForHeaderQueryVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useQuery<GetLocationsForHeaderQuery, GetLocationsForHeaderQueryVariables>(GetLocationsForHeaderDocument, options);
      }
export function useGetLocationsForHeaderLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<GetLocationsForHeaderQuery, GetLocationsForHeaderQueryVariables>) {
          const options = {...defaultOptions, ...baseOptions}
          return Apollo.useLazyQuery<GetLocationsForHeaderQuery, GetLocationsForHeaderQueryVariables>(GetLocationsForHeaderDocument, options);
        }
export type GetLocationsForHeaderQueryHookResult = ReturnType<typeof useGetLocationsForHeaderQuery>;
export type GetLocationsForHeaderLazyQueryHookResult = ReturnType<typeof useGetLocationsForHeaderLazyQuery>;
export type GetLocationsForHeaderQueryResult = Apollo.QueryResult<GetLocationsForHeaderQuery, GetLocationsForHeaderQueryVariables>;