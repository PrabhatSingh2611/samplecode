package digital.windmill.audra.service;

import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.ObjectivesInput;
import digital.windmill.audra.graphql.type.input.UpdateObjectiveInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ObjectiveService {
    /**
     * This method Create Objective
     *
     * @param input    takes mandatory/ optional input needs for new objective creation
     * @param employee parameter to add employee inside objective
     * @return a specific created Objective
     */
    Objective createObjective(CreateObjectiveInput input, Employee employee);

    /**
     * This method will update Objective
     *
     * @param input                takes mandatory/ optional input needs for updating objective
     * @param employee             employe for updating existing objective
     * @param objectiveToBeUpdated specific objective that will be updated
     * @return updated Objective
     */
    Objective updateObjective(UpdateObjectiveInput input, Employee employee, Objective objectiveToBeUpdated);

    /**
     * This method withh find specific Objective
     * This method will return a specific Objective by specific UUID.
     *
     * @param uuid uuid by which we search Objective
     * @return a specific Objective
     */
    Objective findObjectiveByUuid(UUID uuid);

    /**This method will delete a EmployeePosition by a specific value.
     * @param objectiveToBeDeleted Objective of which we should be deleting
     * @return deleted EmployeePosition
     */
    Objective deleteObjective(Objective objectiveToBeDeleted);

    /**
     * This method will return a list of Objective.
     * @param input for query result
     * @return a list of Objective including pagination
     */
    Page<Objective> findAllObjectives(ObjectivesInput input);
}
