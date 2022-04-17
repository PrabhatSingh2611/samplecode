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

    /**
     * This method will create an Employee by a specific value.
     *
     * @param input input of which employee we should create
     * @return created employee
     */
    Employee createEmployee(CreateEmployeeInput input);
}
