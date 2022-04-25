package digital.windmill.audra.graphql.resolver.location;

import digital.windmill.audra.graphql.facade.LocationFacade;
import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.LocationConnectionPayload;
import digital.windmill.audra.graphql.type.LocationPayload;
import digital.windmill.audra.graphql.type.input.LocationInput;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LocationResolver implements GraphQLQueryResolver {

    private LocationFacade locationFacade;

    public LocationPayload location(LocationInput input) {
        return LocationPayload
                .builder()
                .location(locationFacade.findLocationByUuid(input.getUuid()))
                .build();
    }

    public ConnectionPayload<Location> locations() {
        return ConnectionUtils.buildPayload(locationFacade.getLocations());
    }
}



