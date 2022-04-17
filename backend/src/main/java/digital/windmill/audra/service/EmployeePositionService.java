package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.graphql.type.EmployeePosition;

import java.util.UUID;

public interface EmployeePositionService {
    EmployeePositionEntity createEmployeePosition(EmployeePositionEntity employeePosition);

    EmployeePositionEntity updateEmployeePosition(EmployeePosition employeePosition);

    EmployeePositionEntity deleteEmployeePosition(EmployeePosition employeePosition);

    /**
     * This method will search position by an uuid value.
     *
     * @param uuid of which position will be searched in Repository
     * @return a position
     */
    EmployeePositionEntity findEmployeePositionByUuid(UUID uuid);
}
