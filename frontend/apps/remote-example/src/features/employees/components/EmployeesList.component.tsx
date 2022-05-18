import React from 'react';

import { WBox, WButton } from 'wdx';

import { useGetEmployees, useGetSelectedEmployees } from 'features/employees/hooks/employees.hooks';
import { Employee } from 'graphql-generated-types/types';
import { employeesListVar } from 'local-state/reactiveVars';

export default function EmployeesList(): JSX.Element {
    const { data } = useGetEmployees();
    const selectedEmployees = useGetSelectedEmployees();

    return (
        <>
            {data?.employees.items.map((employee) => {
                return (
                    <WBox key={employee.uuid}>
                        {employee.firstName} - Position: {employee.position?.name || 'none'}
                        <WButton
                            onClick={(): Employee[] =>
                                employeesListVar([...employeesListVar(), employee])
                            }
                        >
                            Select
                        </WButton>
                    </WBox>
                );
            })}
            <WBox>
                Selected:
                {selectedEmployees.length ? (
                    <>
                        {selectedEmployees.map((employee) => (
                            <WBox key={employee.uuid}>{employee.firstName}</WBox>
                        ))}
                    </>
                ) : (
                    <WBox>No selected employees</WBox>
                )}
            </WBox>
        </>
    );
}
