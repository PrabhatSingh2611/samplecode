package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.entity.LocationEntity;
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

    @Override public LocationEntity findByUuid(UUID uuid) {
        return locationRepository.findByUuid(uuid)
                .orElseThrow(() -> new DataNotFoundException("Location not found"));
    }

    @Override public List<LocationEntity> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location createLocation(CreateLocationInput input) {
        LocationEntity locationEntity = locationMapper.mapCreateLocationInputToLocationEntity(input);
        LocationEntity savedLocationEntity = locationRepository.save(locationEntity);
        return locationMapper.mapLocationEntityToLocation(savedLocationEntity);
    }

    @Override public Location updateLocation(UpdateLocationInput input, LocationEntity location) {
        location.setName(input.getName());
        return locationMapper.mapLocationEntityToLocation(locationRepository.save(location));
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
    public Location findByUuidMapped(UUID uuid) {
         LocationEntity locationEntity = locationRepository.findByUuid(uuid).orElseThrow(
                () -> new DataNotFoundException("location not found for : " + uuid.toString())
        );
        return locationMapper.mapLocationEntityToLocation(locationEntity);
    }


}
