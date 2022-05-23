package digital.windmill.audra.graphql.resolver.location;

import digital.windmill.audra.graphql.facade.LocationFacade;
import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.LocationCountry;
import digital.windmill.audra.graphql.type.LocationPayload;
import digital.windmill.audra.graphql.type.input.LocationInput;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class LocationResolver implements GraphQLQueryResolver {

    private LocationFacade locationFacade;

    public LocationPayload location(LocationInput input) {
        return LocationPayload
                .builder()
                .location(locationFacade.findLocationByUuid(input.getId()))
                .build();
    }

    public ConnectionPayload<Location> locations() {
        return ConnectionUtils.buildPayload(locationFacade.getLocations());
    }

    public ConnectionPayload<LocationCountry> locationCountries() {
        return ConnectionUtils.buildPayload(
                new PageImpl<>(List.of(
                        LocationCountry.builder().id(UUID.fromString("cabfb51b-41c5-4f4d-afd5-146c796391af")).name("Portugal").build(),
                        LocationCountry.builder().id(UUID.fromString("79761728-8f51-475f-aa04-42385a0dfe36")).name("Spain").build(),
                        LocationCountry.builder().id(UUID.fromString("c6faa656-6da1-450e-b176-6093e22298f6")).name("Ukraine").build())
                ));
    }
}



