import * as Types from '../../../../graphql-generated-types/types';

import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
const defaultOptions = {} as const;
export type GetLocationCountriesForAddLocationModalQueryVariables = Types.Exact<{
  input: Types.LocationCountriesInput;
}>;


export type GetLocationCountriesForAddLocationModalQuery = { __typename?: 'Query', locationCountries: { __typename?: 'LocationCountryConnectionPayload', totalItems: number, items: Array<{ __typename?: 'LocationCountry', id: any, name: string, iconName: string }>, pageInfo: { __typename?: 'PageInfo', currentPage: number, totalPages: number } } };

export type CountryForAddLocationModalFragment = { __typename?: 'LocationCountry', id: any, name: string, iconName: string };

export type PageInfoFragment = { __typename?: 'PageInfo', currentPage: number, totalPages: number };

export const CountryForAddLocationModalFragmentDoc = gql`
    fragment CountryForAddLocationModal on LocationCountry {
  id
  name
  iconName
}
    `;
export const PageInfoFragmentDoc = gql`
    fragment PageInfo on PageInfo {
  currentPage
  totalPages
}
    `;
export const GetLocationCountriesForAddLocationModalDocument = gql`
    query getLocationCountriesForAddLocationModal($input: LocationCountriesInput!) {
  locationCountries(input: $input) {
    items {
      ...CountryForAddLocationModal
    }
    pageInfo {
      ...PageInfo
    }
    totalItems
  }
}
    ${CountryForAddLocationModalFragmentDoc}
${PageInfoFragmentDoc}`;

/**
 * __useGetLocationCountriesForAddLocationModalQuery__
 *
 * To run a query within a React component, call `useGetLocationCountriesForAddLocationModalQuery` and pass it any options that fit your needs.
 * When your component renders, `useGetLocationCountriesForAddLocationModalQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useGetLocationCountriesForAddLocationModalQuery({
 *   variables: {
 *      input: // value for 'input'
 *   },
 * });
 */
export function useGetLocationCountriesForAddLocationModalQuery(baseOptions: Apollo.QueryHookOptions<GetLocationCountriesForAddLocationModalQuery, GetLocationCountriesForAddLocationModalQueryVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useQuery<GetLocationCountriesForAddLocationModalQuery, GetLocationCountriesForAddLocationModalQueryVariables>(GetLocationCountriesForAddLocationModalDocument, options);
      }
export function useGetLocationCountriesForAddLocationModalLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<GetLocationCountriesForAddLocationModalQuery, GetLocationCountriesForAddLocationModalQueryVariables>) {
          const options = {...defaultOptions, ...baseOptions}
          return Apollo.useLazyQuery<GetLocationCountriesForAddLocationModalQuery, GetLocationCountriesForAddLocationModalQueryVariables>(GetLocationCountriesForAddLocationModalDocument, options);
        }
export type GetLocationCountriesForAddLocationModalQueryHookResult = ReturnType<typeof useGetLocationCountriesForAddLocationModalQuery>;
export type GetLocationCountriesForAddLocationModalLazyQueryHookResult = ReturnType<typeof useGetLocationCountriesForAddLocationModalLazyQuery>;
export type GetLocationCountriesForAddLocationModalQueryResult = Apollo.QueryResult<GetLocationCountriesForAddLocationModalQuery, GetLocationCountriesForAddLocationModalQueryVariables>;