package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.graphql.type.input.EmployeePositionsInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface EmployeePositionService {
    EmployeePositionEntity save(EmployeePositionEntity employeePositionEntity);

    EmployeePositionEntity deleteEmployeePosition(EmployeePositionEntity employeePositionEntity);

    EmployeePositionEntity findEmployeePositionByUuid(UUID uuid);

    Page<EmployeePositionEntity> findAll(EmployeePositionsInput input);
}
