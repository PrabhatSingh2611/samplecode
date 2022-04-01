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
    public List<Location> findAllLocation() {
        return locationService
                .findAll()
                .stream()
                .map(locationEntity -> locationMapper.map(locationEntity))
                .collect(Collectors.toList());

    }


    public Location createLocation(String name) {
        return locationMapper.map(locationService.createLocation( name));
    }

    public Location updateLocation(UUID uuid, String name) {

      LocationEntity location =  locationService.findByUuid(uuid);
      location.setName(name);

        return locationMapper.map(locationService.updateLocation( location));
    }
}