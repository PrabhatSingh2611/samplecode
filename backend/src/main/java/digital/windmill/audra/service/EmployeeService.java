package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface EmployeeService {

    EmployeeEntity save(EmployeeEntity employeeEntity);

    EmployeeEntity findEmployeeByUuid(UUID uuid);

    Page<EmployeeEntity> getEmployees(EmployeesInput input);
}
