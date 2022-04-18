package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.facade.LocationFacade;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import digital.windmill.audra.graphql.type.input.UpdateLocationInput;
import digital.windmill.audra.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LocationFacadeImpl implements LocationFacade {
    private LocationService locationService;

    @Transactional(readOnly = true)
    public Location findByUuid(UUID uuid) {
        return locationService.findByUuidMapped(uuid);
    }


    @Override@Transactional(readOnly = true)
    public List<Location> findAllLocation() {
        return locationService.getLocations();
    }

    @Override public Location createLocation(CreateLocationInput input) {
        return locationService.createLocation(input);
    }

    @Override public Location updateLocation(UpdateLocationInput input) {

        LocationEntity location = locationService.findByUuid(input.getUuid());
        return locationService.updateLocation(input, location);
    }
}