import * as Types from '../../../../graphql-generated-types/types';

import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
const defaultOptions = {} as const;
export type GetPoliciesForPolicyListQueryVariables = Types.Exact<{
  input: Types.PoliciesInput;
}>;


export type GetPoliciesForPolicyListQuery = { __typename?: 'Query', policies: { __typename?: 'PolicyConnectionPayload', totalItems: number, items: Array<{ __typename?: 'Policy', id: any, title: string, publicationDate?: any | null, file: { __typename?: 'Resource', id: any, url: string } }>, pageInfo: { __typename?: 'PageInfo', currentPage: number, totalPages: number } } };

export type PolicyForPoliciesListFragment = { __typename?: 'Policy', id: any, title: string, publicationDate?: any | null, file: { __typename?: 'Resource', id: any, url: string } };

export const PolicyForPoliciesListFragmentDoc = gql`
    fragment PolicyForPoliciesList on Policy {
  id
  title
  file {
    id
    url
  }
  publicationDate
}
    `;
export const GetPoliciesForPolicyListDocument = gql`
    query getPoliciesForPolicyList($input: PoliciesInput!) {
  policies(input: $input) {
    items {
      ...PolicyForPoliciesList
    }
    pageInfo {
      currentPage
      totalPages
    }
    totalItems
  }
}
    ${PolicyForPoliciesListFragmentDoc}`;

/**
 * __useGetPoliciesForPolicyListQuery__
 *
 * To run a query within a React component, call `useGetPoliciesForPolicyListQuery` and pass it any options that fit your needs.
 * When your component renders, `useGetPoliciesForPolicyListQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useGetPoliciesForPolicyListQuery({
 *   variables: {
 *      input: // value for 'input'
 *   },
 * });
 */
export function useGetPoliciesForPolicyListQuery(baseOptions: Apollo.QueryHookOptions<GetPoliciesForPolicyListQuery, GetPoliciesForPolicyListQueryVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useQuery<GetPoliciesForPolicyListQuery, GetPoliciesForPolicyListQueryVariables>(GetPoliciesForPolicyListDocument, options);
      }
export function useGetPoliciesForPolicyListLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<GetPoliciesForPolicyListQuery, GetPoliciesForPolicyListQueryVariables>) {
          const options = {...defaultOptions, ...baseOptions}
          return Apollo.useLazyQuery<GetPoliciesForPolicyListQuery, GetPoliciesForPolicyListQueryVariables>(GetPoliciesForPolicyListDocument, options);
        }
export type GetPoliciesForPolicyListQueryHookResult = ReturnType<typeof useGetPoliciesForPolicyListQuery>;
export type GetPoliciesForPolicyListLazyQueryHookResult = ReturnType<typeof useGetPoliciesForPolicyListLazyQuery>;
export type GetPoliciesForPolicyListQueryResult = Apollo.QueryResult<GetPoliciesForPolicyListQuery, GetPoliciesForPolicyListQueryVariables>;