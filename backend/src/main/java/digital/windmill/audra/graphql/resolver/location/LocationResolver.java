package digital.windmill.audra.graphql.resolver.location;

import digital.windmill.audra.graphql.facade.LocationFacade;
import digital.windmill.audra.graphql.type.Location;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class LocationResolver {


    private LocationFacade locationFacade;

    public Location location(UUID uuid) {
        return locationFacade.findLocationByUuid(uuid);
    }

    //----never be used
    public Location findAllLocation(Location location) {
        return locationFacade.findAllLocation(location);
    }

/*---- needs to be removed
   public ConnectionPayload<Location> location(AssetInput input) {
        return ConnectionUtils.buildPayload(facade.findAllAssets(input));
    }
*/
}



