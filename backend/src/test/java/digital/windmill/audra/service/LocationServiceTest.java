package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.repository.LocationRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.LocationMapper;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import digital.windmill.audra.graphql.type.input.UpdateLocationInput;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;
    @Mock
    private LocationMapper locationMapper;

    @InjectMocks
    private LocationServiceImpl service;

    private static final UUID TEST_UUID = UUID.fromString("8201ef3c-1ee0-4920-89e2-7b6c4c539a15");
    private static final String NAME = "47nhdrx";

    //TODO: Rest of UT for LocationService class
    @Test
    void shouldCreateLocation(@Mock CreateLocationInput createLocationInput) {
        when(locationMapper.mapCreateLocationInputToLocationEntity(any(CreateLocationInput.class))).thenReturn(createLocationEntity());
        when(locationRepository.save(any(LocationEntity.class))).thenReturn(createLocationEntity());
        when(locationMapper.mapLocationEntityToLocation(any(LocationEntity.class))).thenReturn(createLocation());

        var result = service.createLocation(createLocationInput);
        Assertions.assertNotNull(result);
        assertEquals(NAME, result.getName());
        assertEquals(TEST_UUID, result.getUuid());
    }

    @Test
    void shouldUpdateLocation(@Mock UpdateLocationInput updateLocationInput,
                              @Mock Location location) {
        when(locationMapper.mapLocationToLocationEntity(any(Location.class))).thenReturn(createLocationEntity());
        when(locationRepository.save(any(LocationEntity.class))).thenReturn(createLocationEntity());
        when(locationMapper.mapLocationEntityToLocation(any(LocationEntity.class))).thenReturn(createLocation());


        var result = service.updateLocation(updateLocationInput, location);
        Assertions.assertNotNull(result);
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(NAME, result.getName());
    }

    @Test
    void shouldGetLocations() {
       when(locationMapper.mapLocationEntityToLocation(any(LocationEntity.class)))
                .thenReturn(createLocation());
        when(locationRepository.findAll((Specification<LocationEntity>) any(),any(PageRequest.class)))
                .thenReturn(createLocationEntityList());
        var result = service.getLocations();
        assertNotNull(result);
        assertEquals(TEST_UUID, result.getContent().get(0).getUuid());
        assertEquals(NAME, result.getContent().get(0).getName());
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

    @Test
    void shouldReturnLocationByUuid() {

        when(locationRepository.findByUuid(any(UUID.class))).thenReturn(Optional.ofNullable(createLocationEntity()));
        when(locationMapper.mapLocationEntityToLocation(any(LocationEntity.class))).thenReturn(createLocation());
        var result = service.findLocationByUuid(TEST_UUID);

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getUuid());
    }

    @Test
    void shouldThrowDataNotFoundWhenUuidIsNull() {
        when(locationRepository.findByUuid(any(UUID.class)))
                .thenThrow(new DataNotFoundException("location not found for :" + TEST_UUID));
        Assertions.assertThrows(DataNotFoundException.class, () -> service.findLocationByUuid(TEST_UUID));
    }

    private Location createLocation() {
        return Location.builder().uuid(TEST_UUID).name(NAME).build();
    }


    private LocationEntity createLocationEntity() {
        return LocationEntity.builder().id(1L).uuid(TEST_UUID).name(NAME).build();
    }

    private List<LocationEntity> createLocalEntityList() {
        List<LocationEntity> locationEntityList = new ArrayList<>();
        locationEntityList.add(createLocationEntity());
        return locationEntityList;
    }
}
