package digital.windmill.audra.graphql.resolver.location;

import digital.windmill.audra.graphql.facade.impl.LocationFacade;
import digital.windmill.audra.graphql.type.location.CreateLocationInput;
import digital.windmill.audra.graphql.type.location.LocationPayload;
import digital.windmill.audra.graphql.type.location.UpdateLocationInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LocationMutationResolver implements GraphQLMutationResolver {

    private LocationFacade locationFacade;

    public LocationPayload createLocation(CreateLocationInput input) {
        return new LocationPayload(locationFacade.createLocation(input));
    }

    public LocationPayload updateLocation(UpdateLocationInput input) {
        return new LocationPayload(locationFacade.updateLocation(input));
    }
}
