package digital.windmill.audra.graphql.resolver.locationCountry;

import digital.windmill.audra.graphql.facade.impl.LocationFacade;
import digital.windmill.audra.graphql.type.location.Location;
import digital.windmill.audra.graphql.type.locationCountry.LocationCountry;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class LocationCountryResolver implements GraphQLResolver<LocationCountry> {

    private LocationFacade locationFacade;

    public List<Location> locations(LocationCountry locationCountry) {
        return locationFacade.findLocationsByCountryUuid(locationCountry.getId());
    }
}
