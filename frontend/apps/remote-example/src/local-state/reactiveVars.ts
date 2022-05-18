import { makeVar } from '@apollo/client';

import { Employee } from 'graphql-generated-types/types';

export const employeesListVar = makeVar<Employee[]>([]);
export const isAuthorizedVar = makeVar(false);
