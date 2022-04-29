package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.LocationSpecification;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.repository.LocationRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    private LocationRepository locationRepository;

    @Override
    public LocationEntity findLocationByUuid(UUID uuid) {
        if(Optional.ofNullable(uuid).isPresent()) {
            return locationRepository.findByUuid(uuid).orElseThrow(
                    () -> new DataNotFoundException("location not found for : " + uuid)
            );
        }
        else
            return null;
    }

    @Override
    public LocationEntity createLocation(LocationEntity locationEntity) {
        return locationRepository.save(locationEntity);
    }

    @Override
    public LocationEntity updateLocation(LocationEntity locationEntity) {
        return locationRepository.save(locationEntity);
    }



    @Override
    public Page<LocationEntity> getLocations() {
                Specification<LocationEntity> specification = LocationSpecification.allLocations();
                PageRequest pagination = PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
                return locationRepository.findAll(specification, pagination);
    }

}
