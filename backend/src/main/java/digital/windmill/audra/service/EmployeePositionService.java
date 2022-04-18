package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.input.CreateEmployeePositionInput;
import digital.windmill.audra.graphql.type.input.UpdateEmployeePositionInput;

import java.util.UUID;

public interface EmployeePositionService {

    /**
     * This method will create a EmployeePosition by a specific value.
     *
     * @param input input of which EmployeePosition we should create
     * @return created EmployeePosition
     */
    EmployeePosition createEmployeePosition(CreateEmployeePositionInput input);


    /**
     * This method will update a EmployeePosition value
     *
     * @param input            input of which EmployeePosition we should update
     * @param employeePosition employeePositionEntity of which EmployeePosition we should update
     * @return updated EmployeePosition
     */
    EmployeePosition updateEmployeePosition(UpdateEmployeePositionInput input,
                                            EmployeePositionEntity employeePosition);


    /**
     * This method will delete a EmployeePosition by a specific value.
     *
     * @param employeePosition employeePositionEntity of which EmployeePosition we should update
     * @return updated EmployeePosition
     */
    EmployeePosition deleteEmployeePosition(EmployeePositionEntity employeePosition);


    /**It takes uuid as input and returns EmployeePositionEntity
     *
     * @param uuid will take uuid as input to search
     * @return searched EmployeePosition
     */
    EmployeePositionEntity findByUuid(UUID uuid);

}
