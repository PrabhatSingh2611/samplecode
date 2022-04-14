package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.graphql.type.EmployeePosition;

import java.util.UUID;

public interface EmployeePositionService {
    EmployeePositionEntity createEmployeePosition(EmployeePositionEntity employeePosition);

    EmployeePositionEntity updateEmployeePosition(EmployeePosition employeePosition);

    EmployeePositionEntity deleteEmployeePosition(EmployeePosition employeePosition);

    EmployeePositionEntity findEmployeePositionByUuid(UUID position);
}
