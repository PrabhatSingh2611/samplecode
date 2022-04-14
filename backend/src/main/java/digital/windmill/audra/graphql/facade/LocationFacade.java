package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.mapper.LocationMapper;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import digital.windmill.audra.graphql.type.input.UpdateLocationInput;
import digital.windmill.audra.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LocationFacade {
    private LocationService locationServiceImpl;
    private LocationMapper locationMapper;

    @Transactional(readOnly = true)
    public Location findLocationByUuid(UUID uuid) {
        return locationMapper.map(locationServiceImpl.findByUuid(uuid));
    }


    @Transactional(readOnly = true)
    public List<Location> findAllLocation() {
        return locationServiceImpl
                .findAll()
                .stream()
                .map(locationEntity -> locationMapper.map(locationEntity))
                .toList();

    }

    public Location createLocation(CreateLocationInput input) {
        return locationMapper.map(locationServiceImpl.createLocation(input));
    }

    public Location updateLocation(UpdateLocationInput input) {

        LocationEntity location = locationServiceImpl.findByUuid(input.getUuid());
        location.setName(input.getName());
        return locationMapper.map(locationServiceImpl.updateLocation(location));
    }
}