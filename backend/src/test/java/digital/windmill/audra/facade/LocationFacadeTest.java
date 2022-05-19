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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationFacadeTest {

    private static final UUID LOCATION_UUID = UUID.randomUUID();

    @Mock
    private LocationService locationService;

    @Mock
    private LocationMapper locationMapper;

    @InjectMocks
    private LocationFacadeImpl locationFacade;

    @Test
    void shouldFindLocationByUuid(@Mock LocationEntity locationEntity,
                                  @Mock Location location) {
        when(locationService.findLocationByUuid(LOCATION_UUID)).thenReturn(locationEntity);
        when(locationMapper.mapLocationEntityToLocation(locationEntity)).thenReturn(location);

        var actualResult = locationFacade.findLocationByUuid(LOCATION_UUID);

        assertEquals(location, actualResult);
    }

    @Test
    void shouldFindAllLocation(@Mock LocationEntity locationEntity,
                               @Mock Location location) {

        when(locationService.getLocations()).thenReturn(createOneItemPage(locationEntity));
        when(locationMapper.mapLocationEntityToLocation(locationEntity)).thenReturn(location);

        var actualResult = locationFacade.getLocations();

        assertEquals(createOneItemPage(location), actualResult);
    }


    @Test
    void shouldCreateLocation(@Mock CreateLocationInput input,
                              @Mock LocationEntity locationEntity,
                              @Mock Location location) {

        when(locationMapper.mapCreateLocationInputToLocationEntity(input)).thenReturn(locationEntity);
        when(locationService.save(locationEntity)).thenReturn(locationEntity);
        when(locationMapper.mapLocationEntityToLocation(locationEntity)).thenReturn(location);

        var actualResult = locationFacade.createLocation(input);

        assertEquals(location, actualResult);
    }

    @Test
    void shouldUpdateLocation(@Mock UpdateLocationInput input,
                              @Mock LocationEntity locationEntity,
                              @Mock Location location) {

        when(locationService.findLocationByUuid(input.getId())).thenReturn(locationEntity);
        when(locationMapper.mapLocationToLocationEntityUpdate(input, locationEntity)).thenReturn(locationEntity);
        when(locationService.save(locationEntity)).thenReturn(locationEntity);
        when(locationMapper.mapLocationEntityToLocation(locationEntity)).thenReturn(location);

        var actualResult = locationFacade.updateLocation(input);

        assertEquals(location, actualResult);
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }
}