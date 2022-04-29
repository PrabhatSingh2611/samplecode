package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import digital.windmill.audra.graphql.type.input.UpdateLocationInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface LocationService {

    /**
     * This method will search location by an uuid value.
     *
     * @param locationEntity of which location will be created with new data
     * @return an updated location
     */
    LocationEntity createLocation(LocationEntity locationEntity);

    /**
     * This method will update location by provided value.
     *
     * @param locationEntity is previous location that will be updated with new data
     * @return an updated location
     */
    LocationEntity updateLocation(LocationEntity locationEntity);
    

    /**
     * This method will search location by an uuid value.
     *
     * @param uuid of which location will be searched in Repository
     * @return required location searched wrapped into Location
     */
    LocationEntity findLocationByUuid(UUID uuid);

    /**
     *
     * @return all location
     */
    Page<LocationEntity> getLocations();
}
