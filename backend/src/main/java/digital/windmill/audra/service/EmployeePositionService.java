package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.EmployeePositionEntity;

import java.util.UUID;

public interface EmployeePositionService {
    EmployeePositionEntity save(EmployeePositionEntity employeePositionEntity);

    EmployeePositionEntity deleteEmployeePosition(EmployeePositionEntity employeePositionEntity);

    EmployeePositionEntity findEmployeePositionByUuid(UUID uuid);
}
