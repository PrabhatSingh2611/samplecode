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

    public Location allLocation(Location location) {
        return (Location) locationFacade.findAllLocation(location);
    }

/*

   public ConnectionPayload<Asset> assets(AssetInput input) {
        return ConnectionUtils.buildPayload(facade.findAllAssets(input));
    }
*/
}



