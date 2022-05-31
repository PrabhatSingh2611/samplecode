package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.mapper.LocationMapper;
import digital.windmill.audra.graphql.type.location.CreateLocationInput;
import digital.windmill.audra.graphql.type.location.Location;
import digital.windmill.audra.graphql.type.location.LocationsInput;
import digital.windmill.audra.graphql.type.location.UpdateLocationInput;
import digital.windmill.audra.service.impl.LocationCountryService;
import digital.windmill.audra.service.impl.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LocationFacade {

    private LocationService locationService;
    private LocationCountryService locationCountryService;
    private LocationMapper locationMapper;

    @Transactional(readOnly = true)
    public Location findLocationByUuid(UUID uuid) {
        return locationMapper.map(locationService.findLocationByUuid(uuid));
    }

    @Transactional(readOnly = true)
    public List<Location> findLocationsByCountryUuid(UUID countryUuid) {
        return locationCountryService.findByUuid(countryUuid).getLocations()
                .stream().map(locationMapper::map).toList();
    }

    @Transactional(readOnly = true)
    public Page<Location> getLocations(LocationsInput input) {
        return locationService.getLocations(input).map(locationMapper::map);
    }

    @Transactional
    public Location createLocation(CreateLocationInput input) {
        var country = locationCountryService.findByUuid(input.getCountry().getId());
        var createdLocation = locationMapper.map(input, country);
        return locationMapper.map(locationService.save(createdLocation));
    }

    @Transactional
    public Location updateLocation(UpdateLocationInput input) {
        var country = locationCountryService.findByUuid(input.getCountry().getId());
        var location = locationService.findLocationByUuid(input.getId());
        var updatedLocation = locationMapper.map(input, location, country);
        return locationMapper.map(locationService.save(updatedLocation));
    }
}