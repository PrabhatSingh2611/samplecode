package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.mapper.LocationMapper;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.service.LocationService;
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
    private LocationMapper locationMapper;

    @Transactional(readOnly = true)
    public Location findLocationByUuid(UUID uuid) {
        return locationMapper.map(locationService.findLocationByUuid(uuid));
    }


    @Transactional(readOnly = true)
    public List<Location> findAllLocation(Location location) {
        return locationService.findAllLocation(location)
                .map(locationMapper::map);
    }
}
