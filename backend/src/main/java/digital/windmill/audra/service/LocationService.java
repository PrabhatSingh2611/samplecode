package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.LocationEntity;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface LocationService {

    LocationEntity save(LocationEntity locationEntity);

    LocationEntity findLocationByUuid(UUID uuid);

    Page<LocationEntity> getLocations();
}
