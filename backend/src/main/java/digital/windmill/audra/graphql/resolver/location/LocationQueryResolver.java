package digital.windmill.audra.graphql.resolver.location;

import digital.windmill.audra.graphql.facade.impl.LocationFacade;
import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.location.Location;
import digital.windmill.audra.graphql.type.location.LocationInput;
import digital.windmill.audra.graphql.type.location.LocationPayload;
import digital.windmill.audra.graphql.type.location.LocationsInput;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LocationQueryResolver implements GraphQLQueryResolver {

    private LocationFacade locationFacade;

    public LocationPayload location(LocationInput input) {
        return LocationPayload
                .builder()
                .location(locationFacade.findLocationByUuid(input.getId()))
                .build();
    }

    public ConnectionPayload<Location> locations(LocationsInput input) {
        return ConnectionUtils.buildPayload(locationFacade.getLocations(input));
    }

}



