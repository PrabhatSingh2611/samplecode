package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface EmployeeFacade {
    @Transactional(readOnly = true)
    Employee findAssetByUuid(UUID uuid);

    @Transactional(readOnly = true)
    Page<Employee> getEmployees(EmployeesInput input);

    Employee createEmployee(CreateEmployeeInput input);
}
