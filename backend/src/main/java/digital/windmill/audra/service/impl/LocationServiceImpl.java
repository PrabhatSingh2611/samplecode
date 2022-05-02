package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.repository.LocationRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.LocationMapper;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import digital.windmill.audra.graphql.type.input.UpdateLocationInput;
import digital.windmill.audra.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public List<Location> getLocations() {
        return locationRepository
                .findAll()
                .stream()
                .map(locationMapper::mapLocationEntityToLocation)
                .toList();
    }

    @Override
    public Location findLocationByUuid(UUID uuid) {
        if (uuid == null) {
            return null;
        }
        var locationEntity = locationRepository.findByUuid(uuid).orElseThrow(
                () -> new DataNotFoundException("location not found for : " + uuid.toString())
        );
        return locationMapper.mapLocationEntityToLocation(locationEntity);
    }


}
