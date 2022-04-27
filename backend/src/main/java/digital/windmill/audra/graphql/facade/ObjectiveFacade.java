package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.DeleteObjectiveInput;
import digital.windmill.audra.graphql.type.input.ObjectivesInput;
import digital.windmill.audra.graphql.type.input.UpdateObjectiveInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ObjectiveFacade {

    /**This method will create an Objective by a specific value.
     * @param input input of which Objective should be create
     * @return created Objective
     */
    Objective createObjective(CreateObjectiveInput input);

    /** This method will update an Objective by a specific value.
     * @param input input of which Objective we should update
     * @return updated Objective
     */
    Objective updateObjective(UpdateObjectiveInput input);


    /**This method will delete an Objective by a specific value
     * @param input input of which Objective we should delete
     * @return a specific deleted Objective
     */
    Objective deleteObjective(DeleteObjectiveInput input);

    /**
     * This method will return a Objective by a specific uuid.
     * @param uuid of which objective we should look
     * @return a specific objective
     */
    Objective findObjectiveByUuid(UUID uuid);

    /**
     * This method will return a list of Objectives.
     * @param input input for query result
     * @return a list of Objective
     */
    Page<Objective> getObjectives(ObjectivesInput input);
}
