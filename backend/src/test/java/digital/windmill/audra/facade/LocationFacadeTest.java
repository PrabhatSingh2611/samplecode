package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.facade.impl.LocationFacadeImpl;
import digital.windmill.audra.graphql.mapper.LocationMapper;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import digital.windmill.audra.graphql.type.input.UpdateLocationInput;
import digital.windmill.audra.service.LocationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationFacadeTest {

    private static final UUID TEST_UUID = UUID.randomUUID();
    private static final UUID SECOND_UUID = UUID.randomUUID();
    private static final Long ID = 1L;
    private static final String NAME = "PcwrDcz";

    @Mock
    private LocationService locationService;

    @Mock
    private LocationMapper locationMapper;

    @InjectMocks
    private LocationFacadeImpl locationFacadeImpl;

    @Test
    void shouldFindLocationByUuid() {
        when(locationService.findLocationByUuid(any(UUID.class)))
                .thenReturn(createLocationEntity());
        when(locationMapper.mapLocationEntityToLocation(any(LocationEntity.class)))
                .thenReturn(createLocation());

        var result = locationFacadeImpl.findLocationByUuid(TEST_UUID);
        assertNotNull(result);
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(NAME, result.getName());
    }

    @Test
    void shouldFindAllLocation() {
        var pagedResponse = new PageImpl(createLocationsEntity());
        when(locationService.getLocations())
                .thenReturn(pagedResponse);
        when(locationMapper.mapLocationEntityToLocation(any(LocationEntity.class)))
                .thenReturn(createLocation());

        var result = locationFacadeImpl.getLocations();
        assertNotNull(result);
        assertEquals(TEST_UUID, result.getContent().get(0).getUuid());
        assertEquals(NAME, result.getContent().get(0).getName());
    }


    @Test
    void shouldCreateLocation(@Mock CreateLocationInput input) {
        when(locationService.createLocation(any(LocationEntity.class)))
                .thenReturn(createLocationEntity());

        when(locationMapper.mapCreateLocationInputToLocationEntity(any(CreateLocationInput.class)))
                .thenReturn(createLocationEntity());
        when(locationMapper.mapLocationEntityToLocation(any(LocationEntity.class)))
                .thenReturn(createLocation());
        var result = locationFacadeImpl.createLocation(input);
        assertNotNull(result);
        assertEquals(NAME, result.getName());
    }

    @Test
    void shouldUpdateLocation() {
       when(locationService.findLocationByUuid(any(UUID.class)))
                .thenReturn(createLocationEntity());
        when(locationMapper.mapLocationToLocationEntityUpdate((any(UpdateLocationInput.class)), any(LocationEntity.class)))
                .thenReturn(createLocationEntity());
        when(locationMapper.mapLocationEntityToLocation(any(LocationEntity.class)))
               .thenReturn(createLocation());
        when(locationService.updateLocation(any(LocationEntity.class)))
                .thenReturn(createLocationEntity());
        var result = locationFacadeImpl.updateLocation(createUpdateLocationInput());

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(NAME, result.getName());
    }

    private UpdateLocationInput createUpdateLocationInput() {
        return UpdateLocationInput.builder()
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }

    private Location createLocation() {
        return Location
                .builder()
                .name(NAME)
                .uuid(TEST_UUID)
                .id(ID)
                .build();
    }

    private LocationEntity createLocationEntity() {
        return LocationEntity.builder()
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }

    private List<LocationEntity> createLocationsEntity() {
        return Arrays.asList(LocationEntity.builder()
                        .uuid(TEST_UUID)
                        .name(NAME)
                        .build(),
                LocationEntity.builder()
                        .uuid(SECOND_UUID)
                        .name(NAME)
                        .build());
    }
}