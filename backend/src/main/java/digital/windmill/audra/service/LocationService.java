package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.repository.LocationRepository;
import digital.windmill.audra.graphql.type.input.LocationInput;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
@AllArgsConstructor
public class LocationService {

    private  LocationRepository locationRepository;

    public LocationEntity findByUuid(UUID uuid) {
        return locationRepository.findByUuid(uuid).orElse(null);
    }

    public  List<LocationEntity> findAll() {
        return  locationRepository.findAll();
    }

    public LocationEntity createLocation(LocationInput input) {
        return locationRepository.save(LocationEntity
                .builder()
                .uuid(UUID.randomUUID())
                .name(input.getName())
                .build());
    }

    public LocationEntity updateLocation(LocationEntity location) {
        return locationRepository.save(location);
    }


}
