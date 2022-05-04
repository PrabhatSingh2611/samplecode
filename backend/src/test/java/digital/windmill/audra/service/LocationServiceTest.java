package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.repository.LocationRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.service.impl.LocationServiceImpl;
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

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationServiceImpl locationService;

    @Test
    void shouldReturnLocationByUuid(@Mock LocationEntity locationEntity) {

        when(locationRepository.findByUuid(any(UUID.class)))
                .thenReturn(Optional.ofNullable(locationEntity));

        var result = locationService.findLocationByUuid(TEST_UUID);
        assertNotNull(result);
        assertEquals(locationEntity, result);
    }

    @Test
    void shouldCreateLocation(@Mock LocationEntity locationEntity){

        when(locationRepository.save(any(LocationEntity.class)))
                .thenReturn(locationEntity);

        var result = locationService.createLocation(locationEntity);
        assertNotNull(result);
        assertSame(locationEntity, result);
    }

    @Test
    void shouldUpdateLocation(@Mock LocationEntity locationEntity) {

        when(locationRepository.save(any(LocationEntity.class)))
                .thenReturn(locationEntity);

        var result = locationService.updateLocation(locationEntity);
        assertNotNull(result);
        assertSame(locationEntity, result);
    }

    @Test
    void shouldReturnAllLocations(@Mock LocationEntity locationEntity) {

        Page<LocationEntity> locationPage = createOneItemPage(locationEntity);
        when(locationRepository.findAll((Specification<LocationEntity>) any(), any(PageRequest.class)))
                .thenReturn(locationPage);
        var result = locationService.getLocations();

        assertNotNull(result);
        assertSame(locationPage, result);
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

    @Test
    void shouldThrowDataNotFoundWhenUuidIsNull() {
        when(locationRepository.findByUuid(any(UUID.class)))
                .thenThrow(new DataNotFoundException("location not found for :" + TEST_UUID));
        Assertions.assertThrows(DataNotFoundException.class, () -> locationService.findLocationByUuid(TEST_UUID));
    }

}
