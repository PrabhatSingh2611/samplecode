package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.DeleteObjectiveInput;
import digital.windmill.audra.graphql.type.input.UpdateObjectiveInput;

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

    Objective findObjectiveByUuid(UUID uuid);
}
