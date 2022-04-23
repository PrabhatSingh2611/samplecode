package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.UpdateObjectiveInput;

public interface ObjectiveFacade {
    Objective createObjective(CreateObjectiveInput input);

    Objective updateObjective(UpdateObjectiveInput input);
}
