package digital.windmill.audra.service;

import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import digital.windmill.audra.graphql.type.input.UpdateLocationInput;

import java.util.List;
import java.util.UUID;

public interface LocationService {

    /**
     * This method will search location by an uuid value.
     *
     * @param input of which location will be created with new data
     * @return an updated location
     */
    Location createLocation(CreateLocationInput input);

    /**
     * This method will update location by provided value.
     *
     * @param input    of which location will be updated with new data
     * @param location is previous location that will be updated with new data i,e. input
     * @return an updated location
     */
    Location updateLocation(UpdateLocationInput input, Location location);

    /**
     * This method will search all the locations,
     *
     * @return required locations searched
     */
    List<Location> getLocations();

    /**
     * This method will search location by an uuid value.
     *
     * @param uuid of which location will be searched in Repository
     * @return required location searched wrapped into Location
     */
    Location findLocationByUuid(UUID uuid);
}
