package digital.windmill.audra.graphql.resolver.location;

import digital.windmill.audra.graphql.facade.LocationFacade;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.LocationInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@AllArgsConstructor
public class LocationMutationResolver implements GraphQLMutationResolver {

    private LocationFacade locationFacade;

    public  Location createLocation(LocationInput input) {
      return locationFacade.createLocation(input);
    }

    public Location updateLocation(LocationInput input){
        return locationFacade.updateLocation(input);
    }
}
