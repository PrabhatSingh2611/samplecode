package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.LocationCountryEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.facade.impl.LocationFacade;
import digital.windmill.audra.graphql.mapper.LocationMapper;
import digital.windmill.audra.graphql.type.input.NodeInput;
import digital.windmill.audra.graphql.type.location.CreateLocationInput;
import digital.windmill.audra.graphql.type.location.Location;
import digital.windmill.audra.graphql.type.location.LocationsInput;
import digital.windmill.audra.graphql.type.location.UpdateLocationInput;
import digital.windmill.audra.service.impl.LocationCountryService;
import digital.windmill.audra.service.impl.LocationService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationFacadeTest {

    private static final UUID LOCATION_UUID = UUID.randomUUID();
    private static final UUID COUNTRY_UUID = UUID.randomUUID();

    @Mock
    private LocationService locationService;
    @Mock
    private LocationCountryService locationCountryService;
    @Mock
    private LocationMapper locationMapper;

    @InjectMocks
    private LocationFacade locationFacade;

    @Test
    void shouldFindLocationByUuid(@Mock LocationEntity locationEntity,
                                  @Mock Location location) {
        when(locationService.findLocationByUuid(LOCATION_UUID)).thenReturn(locationEntity);
        when(locationMapper.map(locationEntity)).thenReturn(location);

        var actualResult = locationFacade.findLocationByUuid(LOCATION_UUID);

        assertEquals(location, actualResult);
    }

    @Test
    void shouldFindAllLocation(@Mock LocationEntity locationEntity,
                               @Mock LocationsInput locationsInput,
                               @Mock Location location) {

        when(locationService.getLocations(locationsInput)).thenReturn(createOneItemPage(locationEntity));
        when(locationMapper.map(locationEntity)).thenReturn(location);

        var actualResult = locationFacade.getLocations(locationsInput);

        assertEquals(createOneItemPage(location), actualResult);
    }


    @Test
    void shouldCreateLocation(@Mock CreateLocationInput input,
                              @Mock LocationEntity locationEntity,
                              @Mock LocationCountryEntity locationCountryEntity,
                              @Mock Location location) {

        when(input.getCountry()).thenReturn(NodeInput.of(COUNTRY_UUID));
        when(locationCountryService.findByUuid(input.getCountry().getId())).thenReturn(locationCountryEntity);
        when(locationMapper.map(input, locationCountryEntity)).thenReturn(locationEntity);
        when(locationService.save(locationEntity)).thenReturn(locationEntity);
        when(locationMapper.map(locationEntity)).thenReturn(location);

        var actualResult = locationFacade.createLocation(input);

        assertEquals(location, actualResult);
    }

    @Test
    void shouldUpdateLocation(@Mock UpdateLocationInput input,
                              @Mock LocationEntity locationEntity,
                              @Mock LocationCountryEntity locationCountryEntity,
                              @Mock Location location) {

        when(input.getCountry()).thenReturn(NodeInput.of(COUNTRY_UUID));
        when(locationCountryService.findByUuid(input.getCountry().getId())).thenReturn(locationCountryEntity);
        when(locationService.findLocationByUuid(input.getId())).thenReturn(locationEntity);
        when(locationMapper.map(input, locationEntity, locationCountryEntity)).thenReturn(locationEntity);
        when(locationService.save(locationEntity)).thenReturn(locationEntity);
        when(locationMapper.map(locationEntity)).thenReturn(location);

        var actualResult = locationFacade.updateLocation(input);

        assertEquals(location, actualResult);
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }
}