package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.ObjectiveEntity;
import digital.windmill.audra.graphql.type.input.ObjectivesInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ObjectiveService {
    /**
     * This method Create Objective
     *
     * @param objectiveEntity takes objectiveEntity as parameter
     * @return created Objective
     */
    ObjectiveEntity createObjective(ObjectiveEntity objectiveEntity);

    /**This method will update Objective
     * @param updatedObjectiveEntity specific objective Entity that will be updated
     * @return updated Objective
     */
    ObjectiveEntity updateObjective(ObjectiveEntity updatedObjectiveEntity);

    /**This method will return a specific Objective by specific UUID.
     * @param uuid uuid by which we search Objective
     * @return a specific Objective
     */
    ObjectiveEntity findObjectiveByUuid(UUID uuid);

    /**This method will delete a EmployeePosition by a specific value.
     * @param objectiveToBeDeleted Objective of which we should be deleting
     * @return deleted EmployeePosition
     */
    ObjectiveEntity deleteObjective(ObjectiveEntity objectiveToBeDeleted);

    /**
     * This method will return a list of Objective.
     * @param input for query result
     * @return a list of Objective including pagination
     */
    Page<ObjectiveEntity> findAllObjectives(ObjectivesInput input);
}
