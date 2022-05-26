import * as Types from '../../../../graphql-generated-types/types';

import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
const defaultOptions = {} as const;
export type GetPoliciesForPoliciesListQueryVariables = Types.Exact<{
  input: Types.PoliciesInput;
}>;


export type GetPoliciesForPoliciesListQuery = { __typename?: 'Query', policies: { __typename?: 'PolicyConnectionPayload', items: Array<{ __typename?: 'Policy', id: any, title: string, publicationDate?: any | null, status: Types.PolicyStatus, file: { __typename?: 'Resource', id: any, url: string, thumbnail?: string | null, mimeType: string }, owner: { __typename?: 'Employee', id: any, firstName?: string | null, lastName?: string | null } }>, pageInfo: { __typename?: 'PageInfo', currentPage: number, totalPages: number } } };

export type PolicyForPolicyListFragment = { __typename?: 'Policy', id: any, title: string, publicationDate?: any | null, status: Types.PolicyStatus, file: { __typename?: 'Resource', id: any, url: string, thumbnail?: string | null, mimeType: string }, owner: { __typename?: 'Employee', id: any, firstName?: string | null, lastName?: string | null } };

export type PolicyForPolicyListResourceFragment = { __typename?: 'Resource', id: any, url: string, thumbnail?: string | null, mimeType: string };

export type PolicyForPolicyListOwnerFragment = { __typename?: 'Employee', id: any, firstName?: string | null, lastName?: string | null };

export type PolicyForPolicyListPageInfoFragment = { __typename?: 'PageInfo', currentPage: number, totalPages: number };

export const PolicyForPolicyListResourceFragmentDoc = gql`
    fragment PolicyForPolicyListResource on Resource {
  id
  url
  thumbnail
  mimeType
}
    `;
export const PolicyForPolicyListOwnerFragmentDoc = gql`
    fragment PolicyForPolicyListOwner on Employee {
  id
  firstName
  lastName
}
    `;
export const PolicyForPolicyListFragmentDoc = gql`
    fragment PolicyForPolicyList on Policy {
  id
  title
  file {
    ...PolicyForPolicyListResource
  }
  publicationDate
  status
  owner {
    ...PolicyForPolicyListOwner
  }
}
    ${PolicyForPolicyListResourceFragmentDoc}
${PolicyForPolicyListOwnerFragmentDoc}`;
export const PolicyForPolicyListPageInfoFragmentDoc = gql`
    fragment PolicyForPolicyListPageInfo on PageInfo {
  currentPage
  totalPages
}
    `;
export const GetPoliciesForPoliciesListDocument = gql`
    query getPoliciesForPoliciesList($input: PoliciesInput!) {
  policies(input: $input) {
    items {
      ...PolicyForPolicyList
    }
    pageInfo {
      ...PolicyForPolicyListPageInfo
    }
  }
}
    ${PolicyForPolicyListFragmentDoc}
${PolicyForPolicyListPageInfoFragmentDoc}`;

/**
 * __useGetPoliciesForPoliciesListQuery__
 *
 * To run a query within a React component, call `useGetPoliciesForPoliciesListQuery` and pass it any options that fit your needs.
 * When your component renders, `useGetPoliciesForPoliciesListQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useGetPoliciesForPoliciesListQuery({
 *   variables: {
 *      input: // value for 'input'
 *   },
 * });
 */
export function useGetPoliciesForPoliciesListQuery(baseOptions: Apollo.QueryHookOptions<GetPoliciesForPoliciesListQuery, GetPoliciesForPoliciesListQueryVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useQuery<GetPoliciesForPoliciesListQuery, GetPoliciesForPoliciesListQueryVariables>(GetPoliciesForPoliciesListDocument, options);
      }
export function useGetPoliciesForPoliciesListLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<GetPoliciesForPoliciesListQuery, GetPoliciesForPoliciesListQueryVariables>) {
          const options = {...defaultOptions, ...baseOptions}
          return Apollo.useLazyQuery<GetPoliciesForPoliciesListQuery, GetPoliciesForPoliciesListQueryVariables>(GetPoliciesForPoliciesListDocument, options);
        }
export type GetPoliciesForPoliciesListQueryHookResult = ReturnType<typeof useGetPoliciesForPoliciesListQuery>;
export type GetPoliciesForPoliciesListLazyQueryHookResult = ReturnType<typeof useGetPoliciesForPoliciesListLazyQuery>;
export type GetPoliciesForPoliciesListQueryResult = Apollo.QueryResult<GetPoliciesForPoliciesListQuery, GetPoliciesForPoliciesListQueryVariables>;