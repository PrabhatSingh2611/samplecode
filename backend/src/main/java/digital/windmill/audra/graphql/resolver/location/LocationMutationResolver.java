package digital.windmill.audra.graphql.resolver.location;

import digital.windmill.audra.graphql.facade.LocationFacade;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import digital.windmill.audra.graphql.type.CreateLocationPayload;
import digital.windmill.audra.graphql.type.UpdateLocationInputPayload;
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
              .item(locationFacade.createLocation(input))
              .build();
    }

    public UpdateLocationInputPayload updateLocation(UpdateLocationInput input){
        return UpdateLocationInputPayload
                .builder()
                .item(locationFacade.updateLocation(input))
                .build();
    }
}
