import * as Types from '../../../../graphql-generated-types/types';

import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
const defaultOptions = {} as const;
export type GetEmployeesForEmployeesListQueryVariables = Types.Exact<{
  input?: Types.InputMaybe<Types.EmployeesInput>;
}>;


export type GetEmployeesForEmployeesListQuery = { __typename?: 'Query', employees: { __typename?: 'EmployeeConnectionPayload', totalItems: number, items: Array<{ __typename?: 'Employee', id: any, firstName?: string | null, lastName?: string | null, role?: Types.EmployeeRole | null, birthday?: any | null, reportingManager?: { __typename?: 'Employee', id: any, firstName?: string | null, lastName?: string | null } | null, position?: { __typename?: 'EmployeePosition', id: any, name?: string | null } | null, location?: { __typename?: 'Location', id: any, country: { __typename?: 'LocationCountry', id: any, name: string } } | null }>, pageInfo: { __typename?: 'PageInfo', currentPage: number, totalPages: number } } };

export type EmployeeForEmployeesListFragment = { __typename?: 'Employee', id: any, firstName?: string | null, lastName?: string | null, role?: Types.EmployeeRole | null, birthday?: any | null, reportingManager?: { __typename?: 'Employee', id: any, firstName?: string | null, lastName?: string | null } | null, position?: { __typename?: 'EmployeePosition', id: any, name?: string | null } | null, location?: { __typename?: 'Location', id: any, country: { __typename?: 'LocationCountry', id: any, name: string } } | null };

export const EmployeeForEmployeesListFragmentDoc = gql`
    fragment EmployeeForEmployeesList on Employee {
  id
  firstName
  lastName
  role
  birthday
  reportingManager {
    id
    firstName
    lastName
  }
  position {
    id
    name
  }
  location {
    id
    country {
      id
      name
    }
  }
}
    `;
export const GetEmployeesForEmployeesListDocument = gql`
    query getEmployeesForEmployeesList($input: EmployeesInput) {
  employees(input: $input) {
    items {
      ...EmployeeForEmployeesList
    }
    pageInfo {
      currentPage
      totalPages
    }
    totalItems
  }
}
    ${EmployeeForEmployeesListFragmentDoc}`;

/**
 * __useGetEmployeesForEmployeesListQuery__
 *
 * To run a query within a React component, call `useGetEmployeesForEmployeesListQuery` and pass it any options that fit your needs.
 * When your component renders, `useGetEmployeesForEmployeesListQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useGetEmployeesForEmployeesListQuery({
 *   variables: {
 *      input: // value for 'input'
 *   },
 * });
 */
export function useGetEmployeesForEmployeesListQuery(baseOptions?: Apollo.QueryHookOptions<GetEmployeesForEmployeesListQuery, GetEmployeesForEmployeesListQueryVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useQuery<GetEmployeesForEmployeesListQuery, GetEmployeesForEmployeesListQueryVariables>(GetEmployeesForEmployeesListDocument, options);
      }
export function useGetEmployeesForEmployeesListLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<GetEmployeesForEmployeesListQuery, GetEmployeesForEmployeesListQueryVariables>) {
          const options = {...defaultOptions, ...baseOptions}
          return Apollo.useLazyQuery<GetEmployeesForEmployeesListQuery, GetEmployeesForEmployeesListQueryVariables>(GetEmployeesForEmployeesListDocument, options);
        }
export type GetEmployeesForEmployeesListQueryHookResult = ReturnType<typeof useGetEmployeesForEmployeesListQuery>;
export type GetEmployeesForEmployeesListLazyQueryHookResult = ReturnType<typeof useGetEmployeesForEmployeesListLazyQuery>;
export type GetEmployeesForEmployeesListQueryResult = Apollo.QueryResult<GetEmployeesForEmployeesListQuery, GetEmployeesForEmployeesListQueryVariables>;