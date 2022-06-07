package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.input.CreateEmployeePositionInput;
import digital.windmill.audra.graphql.type.input.DeleteEmployeePositionInput;
import digital.windmill.audra.graphql.type.input.EmployeePositionsInput;
import digital.windmill.audra.graphql.type.input.UpdateEmployeePositionInput;
import org.springframework.data.domain.Page;

public interface EmployeePositionFacade {

    /**
     * This method will create a EmployeePosition by a specific value.
     *
     * @param input input of which employee position should create
     * @return created EmployeePosition
     */
    EmployeePosition createEmployeePosition(CreateEmployeePositionInput input);

    /**
     * This method will update a EmployeePosition by a specific value.
     *
     * @param input input of which EmployeePosition we should update
     * @return updated EmployeePosition
     */
    EmployeePosition updateEmployeePosition(UpdateEmployeePositionInput input);

    /**
     * This method will delete a EmployeePosition by a specific value.
     *
     * @param input input of which EmployeePosition we should delete
     */
    EmployeePosition deleteEmployeePosition(DeleteEmployeePositionInput input);

    Page<EmployeePosition> getEmployeePositions(EmployeePositionsInput input);
}
