package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.repository.LocationRepository;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import digital.windmill.audra.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {

    private LocationRepository locationRepository;

    @Override public LocationEntity findByUuid(UUID uuid) {
        return locationRepository.findByUuid(uuid).orElse(null);
    }

    @Override public List<LocationEntity> findAll() {
        return locationRepository.findAll();
    }

    @Override public LocationEntity createLocation(CreateLocationInput input) {
        return locationRepository.save(LocationEntity
                .builder()
                .uuid(UUID.randomUUID())
                .name(input.getName())
                .build());
    }

    @Override public LocationEntity updateLocation(LocationEntity location) {
        return locationRepository.save(location);
    }


}
