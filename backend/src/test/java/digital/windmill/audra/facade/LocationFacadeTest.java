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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationFacadeTest {

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private LocationService locationService;

    @Mock
    private LocationMapper locationMapper;

    @InjectMocks
    private LocationFacadeImpl locationFacade;

    @Test
    void shouldFindLocationByUuid(@Mock LocationEntity locationEntity,
                                  @Mock Location location) {

        when(locationService.findLocationByUuid(any(UUID.class)))
                .thenReturn(locationEntity);
        when(locationMapper.mapLocationEntityToLocation(any(LocationEntity.class)))
                .thenReturn(location);

        var result = locationFacade.findLocationByUuid(TEST_UUID);
        assertNotNull(result);
        assertSame(location, result);
    }

    @Test
    void shouldFindAllLocation(@Mock LocationEntity locationEntity,
                               @Mock Location location) {

        Page<LocationEntity> locationPage = createOneItemPage(locationEntity);
        when(locationService.getLocations()).thenReturn(locationPage);
        when(locationMapper.mapLocationEntityToLocation(any(LocationEntity.class)))
                .thenReturn(location);

        var result = locationFacade.getLocations();
        assertNotNull(result);
        assertEquals(locationPage.getContent().get(0).getUuid(), result.getContent().get(0).getUuid());
        assertEquals(locationPage.getContent().get(0).getName(), result.getContent().get(0).getName());
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }


    @Test
    void shouldCreateLocation(@Mock CreateLocationInput input,
                              @Mock LocationEntity locationEntity,
                              @Mock Location location) {

        when(locationService.createLocation(any(LocationEntity.class)))
                .thenReturn(locationEntity);
        when(locationMapper.mapCreateLocationInputToLocationEntity(any(CreateLocationInput.class)))
                .thenReturn(locationEntity);
        when(locationMapper.mapLocationEntityToLocation(any(LocationEntity.class)))
                .thenReturn(location);

        var result = locationFacade.createLocation(input);
        assertNotNull(result);
        assertSame(location, result);

    }

    @Test
    void shouldUpdateLocation(@Mock UpdateLocationInput input,
                              @Mock LocationEntity locationEntity,
                              @Mock Location location) {

        when(input.getUuid()).thenReturn(TEST_UUID);
        when(locationService.findLocationByUuid(any(UUID.class)))
                .thenReturn(locationEntity);
        when(locationMapper.mapLocationToLocationEntityUpdate((any(UpdateLocationInput.class)), any(LocationEntity.class)))
                .thenReturn(locationEntity);
        when(locationMapper.mapLocationEntityToLocation(any(LocationEntity.class)))
                .thenReturn(location);
        when(locationService.updateLocation(any(LocationEntity.class)))
                .thenReturn(locationEntity);
        var result = locationFacade.updateLocation(input);

        assertNotNull(result);
        assertSame(location, result);
    }
}