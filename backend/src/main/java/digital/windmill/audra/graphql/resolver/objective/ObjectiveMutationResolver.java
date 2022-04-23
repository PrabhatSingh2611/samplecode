package digital.windmill.audra.graphql.resolver.objective;

import digital.windmill.audra.graphql.facade.ObjectiveFacade;
import digital.windmill.audra.graphql.type.ObjectivePayload;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.UpdateObjectiveInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ObjectiveMutationResolver implements GraphQLMutationResolver {

    private ObjectiveFacade objectiveFacade;

    public ObjectivePayload createObjective(CreateObjectiveInput input){
       return ObjectivePayload.builder()
                .item(objectiveFacade.createObjective(input))
               .build();
    }

    public ObjectivePayload updateObjective(UpdateObjectiveInput input){
        return ObjectivePayload.builder()
                .item(objectiveFacade.updateObjective(input))
                .build();
    }
}
