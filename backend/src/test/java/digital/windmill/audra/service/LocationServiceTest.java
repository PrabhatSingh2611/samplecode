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

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    private static final UUID TEST_UUID = UUID.randomUUID();
    private static final String NAME = "47nhdrx";

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationServiceImpl locationService;

    @Test
    void shouldReturnLocationByUuid() {
        when(locationRepository.findByUuid(any(UUID.class)))
                .thenReturn(Optional.ofNullable(createLocationEntity()));

        var result = locationService.findLocationByUuid(TEST_UUID);
        assertNotNull(result);
        assertEquals(TEST_UUID,result.getUuid());
        assertEquals(NAME,result.getName());
    }

    @Test
    void shouldCreateLocation(){
        when(locationRepository.save(any(LocationEntity.class)))
                .thenReturn(createLocationEntity());

        var result = locationService.createLocation(createLocationEntity());
        assertNotNull(result);
        assertEquals(TEST_UUID,result.getUuid());
        assertEquals(NAME,result.getName());


    }

    @Test
    void shouldUpdateLocation() {
        when(locationRepository.save(any(LocationEntity.class)))
                .thenReturn(createLocationEntity());

        var result = locationService.updateLocation(createLocationEntity());
        assertNotNull(result);
        assertEquals(TEST_UUID,result.getUuid());
        assertEquals(NAME,result.getName());
    }

    @Test
    void shouldReturnAllLocations() {
        when(locationRepository.findAll((Specification<LocationEntity>) any(), any(PageRequest.class)))
                .thenReturn(createLocationEntityList());
        var result = locationService.getLocations();

        assertNotNull(result);
        assertEquals(2L,result.getTotalElements());
        assertEquals(TEST_UUID,result.getContent().get(0).getUuid());
        assertEquals(NAME,result.getContent().get(0).getName());
    }

    @Test
    void shouldThrowDataNotFoundWhenUuidIsNull() {
        when(locationRepository.findByUuid(any(UUID.class)))
                .thenThrow(new DataNotFoundException("location not found for :" + TEST_UUID));
        Assertions.assertThrows(DataNotFoundException.class, () -> locationService.findLocationByUuid(TEST_UUID));
    }

    @Test
    void shouldReturnNullWhenLocationIsNull() {
        var result = locationService.findLocationByUuid(null);
        assertNull(result);
    }

    private LocationEntity createLocationEntity() {
        return LocationEntity.builder()
                .uuid(TEST_UUID)
                .id(1L)
                .name(NAME)
                .build();
    }

    private Page<LocationEntity> createLocationEntityList() {
        return new PageImpl<>(createLocationsEntity());
    }

    private List<LocationEntity> createLocationsEntity() {
        return Arrays.asList(LocationEntity.builder()
                        .uuid(TEST_UUID)
                        .name(NAME)
                        .build(),
                LocationEntity.builder()
                        .uuid(TEST_UUID)
                        .name(NAME)
                        .build());
    }

}
