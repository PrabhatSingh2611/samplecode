package digital.windmill.audra.graphql.resolver.locationCountry;

import digital.windmill.audra.graphql.facade.impl.LocationCountryFacade;
import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.locationCountry.LocationCountriesInput;
import digital.windmill.audra.graphql.type.locationCountry.LocationCountry;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LocationCountryQueryResolver implements GraphQLQueryResolver {

    private LocationCountryFacade locationCountryFacade;

    public ConnectionPayload<LocationCountry> locationCountries(LocationCountriesInput where) {
        return ConnectionUtils.buildPayload(locationCountryFacade.findLocationCountries(where));
    }

}
