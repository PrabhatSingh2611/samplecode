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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
        Assertions.assertEquals(NAME, result.getName());
        Assertions.assertEquals(TEST_UUID, result.getUuid());
    }

    @Test
    void shouldUpdateLocation(@Mock UpdateLocationInput updateLocationInput,
                              @Mock Location location) {
        when(locationMapper.mapLocationToLocationEntity(any(Location.class))).thenReturn(createLocationEntity());
        when(locationRepository.save(any(LocationEntity.class))).thenReturn(createLocationEntity());
        when(locationMapper.mapLocationEntityToLocation(any(LocationEntity.class))).thenReturn(createLocation());


        var result = service.updateLocation(updateLocationInput, location);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getUuid());
        Assertions.assertEquals(NAME, result.getName());
    }

    @Test
    void shouldGetLocations() {
        when(locationRepository.findAll()).thenReturn(createLocalEntityList());
        when(locationMapper.mapLocationEntityToLocation(any(LocationEntity.class))).thenReturn(createLocation());

        var result = service.getLocations();
        Assertions.assertTrue(!result.isEmpty());
    }

    @Test
    void shouldReturnLocationByUuid() {

        when(locationRepository.findByUuid(any(UUID.class))).thenReturn(Optional.ofNullable(createLocationEntity()));
        when(locationMapper.mapLocationEntityToLocation(any(LocationEntity.class))).thenReturn(createLocation());
        var result = service.findLocationByUuid(TEST_UUID);

        assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getUuid());
    }

    @Test
    void shouldThrowDataNotFoundWhenUuidIsNull() {
        when(locationRepository.findByUuid(any(UUID.class)))
                .thenThrow(new DataNotFoundException("location not found for :" + TEST_UUID));
        Assertions.assertThrows(DataNotFoundException.class, () -> service.findLocationByUuid(TEST_UUID));
    }

    @Test
    void shouldReturnNullWhenLocationIsNull() {
        var result = service.findLocationByUuid(null);
        assertNull(result);
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
