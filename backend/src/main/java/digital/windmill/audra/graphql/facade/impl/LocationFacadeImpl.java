package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.LocationFacade;
import digital.windmill.audra.graphql.mapper.LocationMapper;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import digital.windmill.audra.graphql.type.input.UpdateLocationInput;
import digital.windmill.audra.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class LocationFacadeImpl implements LocationFacade {

    private LocationService locationService;
    private LocationMapper locationMapper;

    @Transactional(readOnly = true)
    public Location findLocationByUuid(UUID uuid) {
        return locationMapper
                .mapLocationEntityToLocation(locationService.findLocationByUuid(uuid));
    }

    @Override
    public Page<Location> getLocations() {
        return locationService.getLocations()
                .map(locationMapper::mapLocationEntityToLocation);
    }


    @Override
    public Location createLocation(CreateLocationInput input) {
        return locationMapper
                .mapLocationEntityToLocation(
                        locationService
                                .createLocation(locationMapper
                                        .mapCreateLocationInputToLocationEntity(input)));

    }

    @Override
    public Location updateLocation(UpdateLocationInput input) {
        var locationToBeUpdated = locationService.findLocationByUuid(input.getUuid());
        var updatedLocationEntity = locationMapper
                .mapLocationToLocationEntityUpdate(input,locationToBeUpdated);
        return locationMapper.mapLocationEntityToLocation(locationService.updateLocation(updatedLocationEntity));
    }
}