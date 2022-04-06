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
import java.util.UUID;

@Component
@AllArgsConstructor
public class LocationResolver implements GraphQLQueryResolver {


    private LocationFacade locationFacade;


    public List<Location> getLocations() {
        return locationFacade.findAllLocation();
    }

    public LocationPayload location(LocationInput input) {
        return LocationPayload
                .builder()
                .item(locationFacade.findLocationByUuid(input.getUuid()))
                        .build();
    }

    public LocationConnectionPayload locations(){
        return LocationConnectionPayload.builder()
                .items(locationFacade.findAllLocation())
                .build();
    }
}



