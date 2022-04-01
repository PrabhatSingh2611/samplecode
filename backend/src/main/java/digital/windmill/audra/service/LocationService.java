package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.repository.LocationRepository;
import digital.windmill.audra.graphql.type.Location;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
@AllArgsConstructor
public class LocationService {

    private static LocationRepository locationRepository;

    public static LocationEntity findByUuid(UUID uuid) {
        return locationRepository.findByUuid(uuid).orElse(null);
    }

    public static List<LocationEntity> findAll(Location location) {
        return  locationRepository.findAll();
    }

    public LocationEntity createLocation(UUID uuid, Location name) {
        return locationRepository.saveAndFlush(LocationEntity
                .builder()
                .uuid(UUID.randomUUID())
                .name(name.getName())
                .build());
    }


    /*public LocationEntity updateLocation(UUID uuid, Location name){

    }*/
}
