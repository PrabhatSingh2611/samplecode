package digital.windmill.audra.graphql.resolver.location;

import digital.windmill.audra.graphql.facade.LocationFacade;
import digital.windmill.audra.graphql.type.LocationPayload;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import digital.windmill.audra.graphql.type.input.UpdateLocationInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class LocationMutationResolver implements GraphQLMutationResolver {

    private LocationFacade locationFacade;

    public LocationPayload createLocation(CreateLocationInput input) {
      return LocationPayload
              .builder()
              .location(locationFacade.createLocation(input))
              .build();
    }

    public LocationPayload updateLocation(UpdateLocationInput input){
        return LocationPayload
                .builder()
                .location(locationFacade.updateLocation(input))
                .build();
    }
}
