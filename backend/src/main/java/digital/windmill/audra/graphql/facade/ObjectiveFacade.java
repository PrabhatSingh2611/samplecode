package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;

public interface ObjectiveFacade {
    Objective createObjective(CreateObjectiveInput input);
}
