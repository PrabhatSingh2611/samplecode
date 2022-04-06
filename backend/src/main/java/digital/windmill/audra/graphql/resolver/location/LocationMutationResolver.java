package digital.windmill.audra.graphql.resolver.location;

import digital.windmill.audra.graphql.facade.LocationFacade;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import digital.windmill.audra.graphql.type.CreateLocationPayload;
import digital.windmill.audra.graphql.type.UpdateLocationPayload;
import digital.windmill.audra.graphql.type.input.UpdateLocationInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class LocationMutationResolver implements GraphQLMutationResolver {

    private LocationFacade locationFacade;

    public CreateLocationPayload createLocation(CreateLocationInput input) {
      return CreateLocationPayload
              .builder()
              .location(locationFacade.createLocation(input))
              .build();
    }

    public UpdateLocationPayload updateLocation(UpdateLocationInput input){
        return UpdateLocationPayload
                .builder()
                .location(locationFacade.updateLocation(input))
                .build();
    }
}
