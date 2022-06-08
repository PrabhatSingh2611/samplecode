import * as Types from '../../../../graphql-generated-types/types';

import { gql } from '@apollo/client';
import { PageInfoFragmentDoc } from './locationCountriesForAddLocationModal.generated';
import * as Apollo from '@apollo/client';
const defaultOptions = {} as const;
export type GetLocationsForLocationsListQueryVariables = Types.Exact<{
  input: Types.LocationsInput;
}>;


export type GetLocationsForLocationsListQuery = { __typename?: 'Query', locations: { __typename?: 'LocationConnectionPayload', totalItems: number, items: Array<{ __typename?: 'Location', id: any, name?: string | null, country: { __typename?: 'LocationCountry', id: any, name: string, iconName: string } }>, pageInfo: { __typename?: 'PageInfo', currentPage: number, totalPages: number } } };

export type LocationForLocationsListFragment = { __typename?: 'Location', id: any, name?: string | null, country: { __typename?: 'LocationCountry', id: any, name: string, iconName: string } };

export type LocationCountryForLocationsListFragment = { __typename?: 'LocationCountry', id: any, name: string, iconName: string };

export const LocationCountryForLocationsListFragmentDoc = gql`
    fragment LocationCountryForLocationsList on LocationCountry {
  id
  name
  iconName
}
    `;
export const LocationForLocationsListFragmentDoc = gql`
    fragment LocationForLocationsList on Location {
  id
  name
  country {
    ...LocationCountryForLocationsList
  }
}
    ${LocationCountryForLocationsListFragmentDoc}`;
export const GetLocationsForLocationsListDocument = gql`
    query getLocationsForLocationsList($input: LocationsInput!) {
  locations(input: $input) {
    items {
      ...LocationForLocationsList
    }
    pageInfo {
      ...PageInfo
    }
    totalItems
  }
}
    ${LocationForLocationsListFragmentDoc}
${PageInfoFragmentDoc}`;

/**
 * __useGetLocationsForLocationsListQuery__
 *
 * To run a query within a React component, call `useGetLocationsForLocationsListQuery` and pass it any options that fit your needs.
 * When your component renders, `useGetLocationsForLocationsListQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useGetLocationsForLocationsListQuery({
 *   variables: {
 *      input: // value for 'input'
 *   },
 * });
 */
export function useGetLocationsForLocationsListQuery(baseOptions: Apollo.QueryHookOptions<GetLocationsForLocationsListQuery, GetLocationsForLocationsListQueryVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useQuery<GetLocationsForLocationsListQuery, GetLocationsForLocationsListQueryVariables>(GetLocationsForLocationsListDocument, options);
      }
export function useGetLocationsForLocationsListLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<GetLocationsForLocationsListQuery, GetLocationsForLocationsListQueryVariables>) {
          const options = {...defaultOptions, ...baseOptions}
          return Apollo.useLazyQuery<GetLocationsForLocationsListQuery, GetLocationsForLocationsListQueryVariables>(GetLocationsForLocationsListDocument, options);
        }
export type GetLocationsForLocationsListQueryHookResult = ReturnType<typeof useGetLocationsForLocationsListQuery>;
export type GetLocationsForLocationsListLazyQueryHookResult = ReturnType<typeof useGetLocationsForLocationsListLazyQuery>;
export type GetLocationsForLocationsListQueryResult = Apollo.QueryResult<GetLocationsForLocationsListQuery, GetLocationsForLocationsListQueryVariables>;