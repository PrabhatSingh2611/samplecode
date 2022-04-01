package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.mapper.LocationMapper;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LocationFacade {
    private LocationService locationService;
    private LocationMapper locationMapper;

    @Transactional(readOnly = true)
    public Location findLocationByUuid(UUID uuid) {
        return locationMapper.map(locationService.findByUuid(uuid));
    }


    @Transactional(readOnly = true)
    public Location findAllLocation(Location location) {
//      return (Location) locationService.findAllLocation(location);
//      .map(LocationMapper::map);
        return locationMapper.map((LocationEntity) locationService.findAll(location));

    }

    @Transactional(readOnly = true)
    public Location createLocation(UUID uuid, Location name) {
        return locationMapper.map(locationService.createLocation(uuid, name));
    }

    public Location updateLocation(UUID uuid, Location name) {


        return name;
    }
}