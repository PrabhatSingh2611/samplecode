package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import digital.windmill.audra.graphql.type.input.UpdateEmployeeInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface EmployeeFacade {

    Employee findEmployeeByUuid(UUID uuid);

    Page<Employee> getEmployees(EmployeesInput input);

    Employee createEmployee(CreateEmployeeInput input);

    Employee updateEmployee(UpdateEmployeeInput input);
}
