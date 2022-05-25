import * as Types from '../../../../graphql-generated-types/types';

import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
const defaultOptions = {} as const;
export type GetLeaveTypesForLeaveRequestDrawerContentQueryVariables = Types.Exact<{
  input: Types.LeaveTypesInput;
}>;


export type GetLeaveTypesForLeaveRequestDrawerContentQuery = { __typename?: 'Query', leaveTypes: { __typename?: 'LeaveTypeConnectionPayload', items: Array<{ __typename?: 'LeaveType', id: any, name: string }> } };

export type LeaveTypeForLeaveRequestDrawerContentFragment = { __typename?: 'LeaveType', id: any, name: string };

export const LeaveTypeForLeaveRequestDrawerContentFragmentDoc = gql`
    fragment LeaveTypeForLeaveRequestDrawerContent on LeaveType {
  id
  name
}
    `;
export const GetLeaveTypesForLeaveRequestDrawerContentDocument = gql`
    query getLeaveTypesForLeaveRequestDrawerContent($input: LeaveTypesInput!) {
  leaveTypes(input: $input) {
    items {
      ...LeaveTypeForLeaveRequestDrawerContent
    }
  }
}
    ${LeaveTypeForLeaveRequestDrawerContentFragmentDoc}`;

/**
 * __useGetLeaveTypesForLeaveRequestDrawerContentQuery__
 *
 * To run a query within a React component, call `useGetLeaveTypesForLeaveRequestDrawerContentQuery` and pass it any options that fit your needs.
 * When your component renders, `useGetLeaveTypesForLeaveRequestDrawerContentQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useGetLeaveTypesForLeaveRequestDrawerContentQuery({
 *   variables: {
 *      input: // value for 'input'
 *   },
 * });
 */
export function useGetLeaveTypesForLeaveRequestDrawerContentQuery(baseOptions: Apollo.QueryHookOptions<GetLeaveTypesForLeaveRequestDrawerContentQuery, GetLeaveTypesForLeaveRequestDrawerContentQueryVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useQuery<GetLeaveTypesForLeaveRequestDrawerContentQuery, GetLeaveTypesForLeaveRequestDrawerContentQueryVariables>(GetLeaveTypesForLeaveRequestDrawerContentDocument, options);
      }
export function useGetLeaveTypesForLeaveRequestDrawerContentLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<GetLeaveTypesForLeaveRequestDrawerContentQuery, GetLeaveTypesForLeaveRequestDrawerContentQueryVariables>) {
          const options = {...defaultOptions, ...baseOptions}
          return Apollo.useLazyQuery<GetLeaveTypesForLeaveRequestDrawerContentQuery, GetLeaveTypesForLeaveRequestDrawerContentQueryVariables>(GetLeaveTypesForLeaveRequestDrawerContentDocument, options);
        }
export type GetLeaveTypesForLeaveRequestDrawerContentQueryHookResult = ReturnType<typeof useGetLeaveTypesForLeaveRequestDrawerContentQuery>;
export type GetLeaveTypesForLeaveRequestDrawerContentLazyQueryHookResult = ReturnType<typeof useGetLeaveTypesForLeaveRequestDrawerContentLazyQuery>;
export type GetLeaveTypesForLeaveRequestDrawerContentQueryResult = Apollo.QueryResult<GetLeaveTypesForLeaveRequestDrawerContentQuery, GetLeaveTypesForLeaveRequestDrawerContentQueryVariables>;