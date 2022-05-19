package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import digital.windmill.audra.graphql.type.input.UpdateLocationInput;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface LocationFacade {

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

    /**
     * This method will return a specific location by specific UUID.
     *
     * @param uuid uuid by which we search location
     * @return a specific location
     */
    @Transactional(readOnly = true)
    Location findLocationByUuid(UUID uuid);

    /**
     * @return all Locations
     */
    Page<Location> getLocations();
}
