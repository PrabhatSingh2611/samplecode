package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.LocationSpecification;
import digital.windmill.audra.dao.repository.LocationRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.LocationMapper;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import digital.windmill.audra.graphql.type.input.UpdateLocationInput;
import digital.windmill.audra.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {

    private LocationRepository locationRepository;
    private LocationMapper locationMapper;

    @Override
    public Location createLocation(CreateLocationInput input) {
        var locationEntity = locationMapper.mapCreateLocationInputToLocationEntity(input);
        var savedLocationEntity = locationRepository.save(locationEntity);
        return locationMapper.mapLocationEntityToLocation(savedLocationEntity);
    }

    @Override
    public Location updateLocation(UpdateLocationInput input, Location location) {
        location.setName(input.getName());
        return locationMapper
                .mapLocationEntityToLocation(
                        locationRepository
                                .save(locationMapper.mapLocationToLocationEntity(location)));
    }

     @Override
    public Location findLocationByUuid(UUID uuid) {
        var locationEntity = locationRepository.findByUuid(uuid).orElseThrow(
                () -> new DataNotFoundException("location not found for : " + uuid.toString())
        );
        return locationMapper.mapLocationEntityToLocation(locationEntity);
    }

    @Override
    public Page<Location> getLocations() {
        var spec = LocationSpecification.getAllLocations();
        return locationRepository.findAll(spec.getKey(),spec.getValue())
                .map(locationMapper::mapLocationEntityToLocation);
    }

}
