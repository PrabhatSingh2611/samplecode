package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.repository.LocationRepository;
import digital.windmill.audra.graphql.type.Location;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
@AllArgsConstructor
public class LocationService {

    private static LocationRepository locationRepository;

    public static LocationEntity findLocationByUuid(UUID uuid) {
        return locationRepository.findLocationByUuid(uuid).orElse(null);
    }

    public static List<LocationEntity> findAllLocation(Location location) {
        return locationRepository.findAll();
    }

}
