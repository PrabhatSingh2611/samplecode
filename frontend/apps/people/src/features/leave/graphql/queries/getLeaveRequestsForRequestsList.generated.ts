import * as Types from '../../../../graphql-generated-types/types';

import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
const defaultOptions = {} as const;
export type GetLeaveRequestsForRequestsListQueryVariables = Types.Exact<{
  input: Types.LeaveRequestsInput;
}>;


export type GetLeaveRequestsForRequestsListQuery = { __typename?: 'Query', leaveRequests: { __typename?: 'LeaveRequestConnectionPayload', totalItems: number, items: Array<{ __typename?: 'LeaveRequest', id: any, numberOfDays: number, requestDate: any, startDate: any, endDate: any, status: Types.LeaveRequestStatus, comment?: string | null, employee: { __typename?: 'Employee', id: any, firstName?: string | null, lastName?: string | null, avatar?: string | null, position?: { __typename?: 'EmployeePosition', id: any, name?: string | null } | null }, type: { __typename?: 'LeaveType', id: any, name: string } }>, pageInfo: { __typename?: 'PageInfo', currentPage: number, totalPages: number } } };

export type LeaveRequestForRequestsListFragment = { __typename?: 'LeaveRequest', id: any, numberOfDays: number, requestDate: any, startDate: any, endDate: any, status: Types.LeaveRequestStatus, comment?: string | null, employee: { __typename?: 'Employee', id: any, firstName?: string | null, lastName?: string | null, avatar?: string | null, position?: { __typename?: 'EmployeePosition', id: any, name?: string | null } | null }, type: { __typename?: 'LeaveType', id: any, name: string } };

export const LeaveRequestForRequestsListFragmentDoc = gql`
    fragment leaveRequestForRequestsList on LeaveRequest {
  id
  numberOfDays
  requestDate
  startDate
  endDate
  employee {
    id
    firstName
    lastName
    position {
      id
      name
    }
    avatar
  }
  status
  comment
  type {
    id
    name
  }
}
    `;
export const GetLeaveRequestsForRequestsListDocument = gql`
    query getLeaveRequestsForRequestsList($input: LeaveRequestsInput!) {
  leaveRequests(input: $input) {
    items {
      ...leaveRequestForRequestsList
    }
    pageInfo {
      currentPage
      totalPages
    }
    totalItems
  }
}
    ${LeaveRequestForRequestsListFragmentDoc}`;

/**
 * __useGetLeaveRequestsForRequestsListQuery__
 *
 * To run a query within a React component, call `useGetLeaveRequestsForRequestsListQuery` and pass it any options that fit your needs.
 * When your component renders, `useGetLeaveRequestsForRequestsListQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useGetLeaveRequestsForRequestsListQuery({
 *   variables: {
 *      input: // value for 'input'
 *   },
 * });
 */
export function useGetLeaveRequestsForRequestsListQuery(baseOptions: Apollo.QueryHookOptions<GetLeaveRequestsForRequestsListQuery, GetLeaveRequestsForRequestsListQueryVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useQuery<GetLeaveRequestsForRequestsListQuery, GetLeaveRequestsForRequestsListQueryVariables>(GetLeaveRequestsForRequestsListDocument, options);
      }
export function useGetLeaveRequestsForRequestsListLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<GetLeaveRequestsForRequestsListQuery, GetLeaveRequestsForRequestsListQueryVariables>) {
          const options = {...defaultOptions, ...baseOptions}
          return Apollo.useLazyQuery<GetLeaveRequestsForRequestsListQuery, GetLeaveRequestsForRequestsListQueryVariables>(GetLeaveRequestsForRequestsListDocument, options);
        }
export type GetLeaveRequestsForRequestsListQueryHookResult = ReturnType<typeof useGetLeaveRequestsForRequestsListQuery>;
export type GetLeaveRequestsForRequestsListLazyQueryHookResult = ReturnType<typeof useGetLeaveRequestsForRequestsListLazyQuery>;
export type GetLeaveRequestsForRequestsListQueryResult = Apollo.QueryResult<GetLeaveRequestsForRequestsListQuery, GetLeaveRequestsForRequestsListQueryVariables>;