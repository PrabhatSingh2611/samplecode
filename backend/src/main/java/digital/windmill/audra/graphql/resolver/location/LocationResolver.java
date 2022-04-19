package digital.windmill.audra.graphql.resolver.location;

import digital.windmill.audra.graphql.facade.LocationFacade;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.LocationConnectionPayload;
import digital.windmill.audra.graphql.type.LocationPayload;
import digital.windmill.audra.graphql.type.input.LocationInput;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class LocationResolver implements GraphQLQueryResolver {

    private LocationFacade locationFacade;

    public LocationPayload location(LocationInput input) {
        return LocationPayload
                .builder()
                .location(locationFacade.findByUuid(input.getUuid()))
                .build();
    }

    public LocationConnectionPayload locations() {
        return LocationConnectionPayload
                .builder()
                .items(locationFacade.findAllLocation())
                .build();
    }
}



