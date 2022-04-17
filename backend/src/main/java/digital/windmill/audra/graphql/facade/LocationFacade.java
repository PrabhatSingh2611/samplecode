package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import digital.windmill.audra.graphql.type.input.UpdateLocationInput;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface LocationFacade {
    @Transactional(readOnly = true)
    Location findLocationByUuid(UUID uuid);

    @Transactional(readOnly = true)
    List<Location> findAllLocation();


    /**
     * This method will create a new location by a value.
     *
     * @param input of which location will be created
     * @return an updated location
     */
    Location createLocation(CreateLocationInput input);


    /**
     * This method will update location by a value.
     *
     * @param input of which location will be updated
     * @return an updated location
     */
    Location updateLocation(UpdateLocationInput input);
}
