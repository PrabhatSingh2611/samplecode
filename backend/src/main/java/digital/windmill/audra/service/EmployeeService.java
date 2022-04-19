package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface EmployeeService {

    /**
     * This method will create employee by provided input.
     *
     * @param location         which is location detail of employee being created
     * @param input            which is required information of employee like firstName, lastName, birthday, etc
     * @param employeePosition which is position detail of employee being created
     * @return an employee created
     */
    Employee createEmployee(CreateEmployeeInput input,
                            EmployeePosition employeePosition,
                            Location location);

    /**
     * This method will search employee by an uuid value.
     *
     * @param uuid of which employee will be searched in Repository
     * @return required employee searched wrapped into Employee
     */
    Employee findEmployeeByUuid(UUID uuid);

    /**
     * This method will search employees by an input value.
     *
     * @param input of which employees will be searched in Repository
     * @return required employees searched wrapped into Employee
     */
    Page<Employee> getEmployees(EmployeesInput input);
}
