import * as Types from '../../../../graphql-generated-types/types';

import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
const defaultOptions = {} as const;
export type GetAssetsForAssetsListQueryVariables = Types.Exact<{
  input: Types.AssetsInput;
}>;


export type GetAssetsForAssetsListQuery = { __typename?: 'Query', assets: { __typename?: 'AssetConnectionPayload', totalItems: number, items: Array<{ __typename?: 'Asset', id: any, title?: string | null, serialNumber: string, waybillDate: any, tagNumber?: string | null, type: { __typename?: 'AssetType', id: any, title?: string | null, icon?: string | null }, location?: { __typename?: 'Location', id: any, country: string, details?: string | null, flagIcon: string } | null, assignee?: { __typename?: 'Employee', id: any, firstName?: string | null, lastName?: string | null, avatar?: string | null, position?: { __typename?: 'EmployeePosition', id: any, name?: string | null } | null } | null }>, pageInfo: { __typename?: 'PageInfo', currentPage: number, totalPages: number } } };

export type AssetsItemFragment = { __typename?: 'Asset', id: any, title?: string | null, serialNumber: string, waybillDate: any, tagNumber?: string | null, type: { __typename?: 'AssetType', id: any, title?: string | null, icon?: string | null }, location?: { __typename?: 'Location', id: any, country: string, details?: string | null, flagIcon: string } | null, assignee?: { __typename?: 'Employee', id: any, firstName?: string | null, lastName?: string | null, avatar?: string | null, position?: { __typename?: 'EmployeePosition', id: any, name?: string | null } | null } | null };

export type AssetsTypeFragment = { __typename?: 'AssetType', id: any, title?: string | null, icon?: string | null };

export type AssetsLocationFragment = { __typename?: 'Location', id: any, country: string, details?: string | null, flagIcon: string };

export type AssetsAssigneeFragment = { __typename?: 'Employee', id: any, firstName?: string | null, lastName?: string | null, avatar?: string | null, position?: { __typename?: 'EmployeePosition', id: any, name?: string | null } | null };

export type AssetsAssigneeEmployeeFragment = { __typename?: 'EmployeePosition', id: any, name?: string | null };

export type PageInfoFragment = { __typename?: 'PageInfo', currentPage: number, totalPages: number };

export const AssetsTypeFragmentDoc = gql`
    fragment AssetsType on AssetType {
  id
  title
  icon
}
    `;
export const AssetsLocationFragmentDoc = gql`
    fragment AssetsLocation on Location {
  id
  country
  details
  flagIcon
}
    `;
export const AssetsAssigneeEmployeeFragmentDoc = gql`
    fragment AssetsAssigneeEmployee on EmployeePosition {
  id
  name
}
    `;
export const AssetsAssigneeFragmentDoc = gql`
    fragment AssetsAssignee on Employee {
  id
  firstName
  lastName
  avatar
  position {
    ...AssetsAssigneeEmployee
  }
}
    ${AssetsAssigneeEmployeeFragmentDoc}`;
export const AssetsItemFragmentDoc = gql`
    fragment AssetsItem on Asset {
  id
  title
  serialNumber
  waybillDate
  tagNumber
  type {
    ...AssetsType
  }
  location {
    ...AssetsLocation
  }
  assignee {
    ...AssetsAssignee
  }
}
    ${AssetsTypeFragmentDoc}
${AssetsLocationFragmentDoc}
${AssetsAssigneeFragmentDoc}`;
export const PageInfoFragmentDoc = gql`
    fragment PageInfo on PageInfo {
  currentPage
  totalPages
}
    `;
export const GetAssetsForAssetsListDocument = gql`
    query getAssetsForAssetsList($input: AssetsInput!) {
  assets(input: $input) {
    items {
      ...AssetsItem
    }
    pageInfo {
      ...PageInfo
    }
    totalItems
  }
}
    ${AssetsItemFragmentDoc}
${PageInfoFragmentDoc}`;

/**
 * __useGetAssetsForAssetsListQuery__
 *
 * To run a query within a React component, call `useGetAssetsForAssetsListQuery` and pass it any options that fit your needs.
 * When your component renders, `useGetAssetsForAssetsListQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useGetAssetsForAssetsListQuery({
 *   variables: {
 *      input: // value for 'input'
 *   },
 * });
 */
export function useGetAssetsForAssetsListQuery(baseOptions: Apollo.QueryHookOptions<GetAssetsForAssetsListQuery, GetAssetsForAssetsListQueryVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useQuery<GetAssetsForAssetsListQuery, GetAssetsForAssetsListQueryVariables>(GetAssetsForAssetsListDocument, options);
      }
export function useGetAssetsForAssetsListLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<GetAssetsForAssetsListQuery, GetAssetsForAssetsListQueryVariables>) {
          const options = {...defaultOptions, ...baseOptions}
          return Apollo.useLazyQuery<GetAssetsForAssetsListQuery, GetAssetsForAssetsListQueryVariables>(GetAssetsForAssetsListDocument, options);
        }
export type GetAssetsForAssetsListQueryHookResult = ReturnType<typeof useGetAssetsForAssetsListQuery>;
export type GetAssetsForAssetsListLazyQueryHookResult = ReturnType<typeof useGetAssetsForAssetsListLazyQuery>;
export type GetAssetsForAssetsListQueryResult = Apollo.QueryResult<GetAssetsForAssetsListQuery, GetAssetsForAssetsListQueryVariables>;