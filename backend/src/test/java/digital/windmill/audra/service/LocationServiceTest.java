package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.repository.LocationRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.location.LocationsInput;
import digital.windmill.audra.service.impl.LocationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    private static final UUID LOCATION_UUID = UUID.randomUUID();

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationService locationService;

    @Test
    void shouldReturnLocationByUuid(@Mock LocationEntity locationEntity) {
        when(locationRepository.findByUuid(LOCATION_UUID))
                .thenReturn(Optional.ofNullable(locationEntity));

        var result = locationService.findLocationByUuid(LOCATION_UUID);
        assertNotNull(result);
        assertEquals(locationEntity, result);
    }

    @Test
    void shouldSaveLocation(@Mock LocationEntity locationEntity) {
        when(locationRepository.save(locationEntity)).thenReturn(locationEntity);

        var result = locationService.save(locationEntity);
        assertNotNull(result);
        assertSame(locationEntity, result);
    }

    @Test
    void shouldReturnAllLocations(@Mock LocationEntity locationEntity, @Mock LocationsInput locationsInput) {
        var locationPage = createOneItemPage(locationEntity);
        when(locationRepository.findAll((Specification<LocationEntity>) any(), any(PageRequest.class)))
                .thenReturn(locationPage);
        var result = locationService.getLocations(locationsInput);

        assertNotNull(result);
        assertSame(locationPage, result);
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

    @Test
    void shouldThrowDataNotFoundWhenUuidIsNull() {
        when(locationRepository.findByUuid(any(UUID.class)))
                .thenThrow(new DataNotFoundException("Location not found " + LOCATION_UUID));
        Assertions.assertThrows(DataNotFoundException.class, () -> locationService.findLocationByUuid(LOCATION_UUID));
    }

}
