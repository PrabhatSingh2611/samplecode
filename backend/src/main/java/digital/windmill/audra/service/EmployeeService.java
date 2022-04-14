package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import digital.windmill.audra.graphql.type.input.NodeInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface EmployeeService {
    EmployeeEntity findByUuid(UUID uuid);

    EmployeeEntity findByLocation(NodeInput location);

    Page<EmployeeEntity> findAll(EmployeesInput input);

    Employee createEmployee(CreateEmployeeInput input,
                            EmployeeEntity employeeEntity,
                            EmployeePositionEntity employeePositionEntity,
                            LocationEntity locationEntity);
}
