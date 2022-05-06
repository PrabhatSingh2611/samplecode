package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.ObjectiveEntity;
import digital.windmill.audra.graphql.type.input.ObjectivesInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ObjectiveService {
    /**This method Create Objective
     * @param entity by which Objective entity will be created
     * @return created Objective
     */
    ObjectiveEntity createObjective(ObjectiveEntity entity);

    /**This method will update Objective
     * @param entity specific objective Entity that will be updated
     * @return updated Objective Entity
     */
    ObjectiveEntity updateObjective(ObjectiveEntity entity);

    /**This method will return a specific Objective by specific UUID.
     * @param uuid uuid by which we search Objective Entity
     * @return a specific Objective Entity
     */
    ObjectiveEntity findObjectiveByUuid(UUID uuid);

    /**This method will delete an Objective by a specific value.
     * @param objectiveToBeDeleted Objective of which we should be deleting
     * @return deleted objective Entity
     */
    ObjectiveEntity deleteObjective(ObjectiveEntity objectiveToBeDeleted);

    /**
     * This method will return a list of Objective.
     * @param input for query result
     * @return a list of Objective Entity including pagination
     */
    Page<ObjectiveEntity> findAllObjectives(ObjectivesInput input);
}
