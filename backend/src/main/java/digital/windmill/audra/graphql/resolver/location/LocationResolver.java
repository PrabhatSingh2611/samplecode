package digital.windmill.audra.graphql.resolver.location;

import digital.windmill.audra.graphql.facade.LocationFacade;
import digital.windmill.audra.graphql.type.Location;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class LocationResolver implements GraphQLQueryResolver {


    private LocationFacade locationFacade;

    public Location location(UUID uuid) {
        return locationFacade.findLocationByUuid(uuid);
    }

    //----never be used
    public List<Location> getLocations( ) {
        return locationFacade.findAllLocation( );
    }

/*---- needs to be removed
   public ConnectionPayload<Location> location(AssetInput input) {
        return ConnectionUtils.buildPayload(facade.findAllAssets(input));
    }
*/
}



