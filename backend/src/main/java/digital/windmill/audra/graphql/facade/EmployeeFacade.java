package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface EmployeeFacade {
    /**It return Employee by taking uuid as input
     * @param uuid it used this uuid to give service and take EmployeeEntity
     * @return it returns a specific Employee
     */
    @Transactional(readOnly = true)
    Employee findEmployeeByUuid(UUID uuid);

    /**It Takes EmployeesInput as input and gives all employee in database
     * @param input will be a EmployeesInput type
     * @return all Employee matching to given input
     */
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
