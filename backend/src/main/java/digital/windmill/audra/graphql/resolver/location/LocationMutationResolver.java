package digital.windmill.audra.graphql.resolver.location;

import digital.windmill.audra.graphql.facade.LocationFacade;
import digital.windmill.audra.graphql.type.Location;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.UUID;


@Component
@AllArgsConstructor
public class LocationMutationResolver implements GraphQLMutationResolver {

    private LocationFacade locationFacade;

    public  Location createLocation(String locationName) {
      return locationFacade.createLocation(locationName);
    }

    public Location updateLocation(UUID uuid,String locationName){
        return locationFacade.updateLocation(uuid,locationName);
    }
}
