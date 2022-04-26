package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.LocationSpecification;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.repository.LocationRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.LocationMapper;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import digital.windmill.audra.graphql.type.input.UpdateLocationInput;
import digital.windmill.audra.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.beans.support.PagedListHolder.DEFAULT_PAGE_SIZE;


@Component
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

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
        if(uuid==null) {
            return null;
        }
        var locationEntity = locationRepository.findByUuid(uuid).orElseThrow(
                () -> new DataNotFoundException("location not found for : " + uuid.toString())
        );
        return locationMapper.mapLocationEntityToLocation(locationEntity);
    }

    @Override
    public Page<Location> getLocations() {
                Specification<LocationEntity> specification = LocationSpecification.allLocations();
                PageRequest pagination = PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
                return locationRepository.findAll(specification, pagination)
                .map(locationMapper::mapLocationEntityToLocation);
    }

}
