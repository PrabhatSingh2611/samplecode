import { useReactiveVar } from '@apollo/client';

import {
    GetEmployeesForEmployeesListQueryHookResult,
    useGetEmployeesForEmployeesListQuery,
} from 'features/employees/graphql/queries/employeesForEmployeesList.generated';
import { Employee } from 'graphql-generated-types/types';
import { employeesListVar } from 'local-state/reactiveVars';

export const useGetEmployees = (): GetEmployeesForEmployeesListQueryHookResult =>
    useGetEmployeesForEmployeesListQuery();

export const useGetSelectedEmployees = (): Employee[] => useReactiveVar(employeesListVar);
