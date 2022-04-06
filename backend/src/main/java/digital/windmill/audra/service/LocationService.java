package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.repository.LocationRepository;
import digital.windmill.audra.graphql.type.CreateLocationInput;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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

    public List<LocationEntity> findAll() {
        return locationRepository.findAll();
    }

    public LocationEntity createLocation(CreateLocationInput input) {
        return locationRepository.save(LocationEntity
                .builder()
                .uuid(input.getUuid())
                .name(input.getName())
                .build());
    }

    public LocationEntity updateLocation(LocationEntity location) {
        return locationRepository.save(location);
    }


}
