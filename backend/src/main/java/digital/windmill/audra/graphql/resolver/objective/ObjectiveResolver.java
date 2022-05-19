package digital.windmill.audra.graphql.resolver.objective;

import digital.windmill.audra.graphql.facade.ObjectiveFacade;
import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.ObjectivePayload;
import digital.windmill.audra.graphql.type.input.ObjectiveInput;
import digital.windmill.audra.graphql.type.input.ObjectivesInput;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ObjectiveResolver implements GraphQLQueryResolver {

    private ObjectiveFacade objectiveFacade;

    public ObjectivePayload objective(ObjectiveInput objectiveInput) {
        return ObjectivePayload.builder()
                .item(objectiveFacade.findObjectiveByUuid(objectiveInput.getId()))
                .build();
    }

    public ConnectionPayload<Objective> objectives(ObjectivesInput input) {
        return ConnectionUtils.buildPayload(objectiveFacade.getObjectives(input));
    }
}
