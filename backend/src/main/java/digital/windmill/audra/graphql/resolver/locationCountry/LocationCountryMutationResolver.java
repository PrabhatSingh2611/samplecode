package digital.windmill.audra.graphql.resolver.locationCountry;

import digital.windmill.audra.graphql.facade.impl.LocationCountryFacade;
import digital.windmill.audra.graphql.type.locationCountry.CreateLocationCountryInput;
import digital.windmill.audra.graphql.type.locationCountry.LocationCountryPayload;
import digital.windmill.audra.graphql.type.locationCountry.PatchLocationCountryInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LocationCountryMutationResolver implements GraphQLMutationResolver {

    private LocationCountryFacade locationCountryFacade;

    public LocationCountryPayload createLocationCountry(CreateLocationCountryInput input) {
        return new LocationCountryPayload(locationCountryFacade.createLocationCountry(input));
    }

    public LocationCountryPayload patchLocationCountry(PatchLocationCountryInput input) {
        return new LocationCountryPayload(locationCountryFacade.patchLocationCountry(input));
    }
}
