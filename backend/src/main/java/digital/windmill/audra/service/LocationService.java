package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;

import java.util.List;
import java.util.UUID;

public interface LocationService {
    LocationEntity findByUuid(UUID uuid);

    List<LocationEntity> findAll();

    LocationEntity createLocation(CreateLocationInput input);

    LocationEntity updateLocation(LocationEntity location);
}
