package digital.windmill.audra.facade;

import digital.windmill.audra.graphql.facade.impl.LocationFacadeImpl;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import digital.windmill.audra.graphql.type.input.UpdateLocationInput;
import digital.windmill.audra.service.LocationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationFacadeTest {

    @Mock
    private LocationService locationService;

    @InjectMocks
    private LocationFacadeImpl locationFacade;

    private static final UUID TEST_UUID = UUID.fromString("0069e6ad-d356-472f-99cc-9256565a02a9");
    private static final Long ID = 1L;
    private static final String NAME = "PcwrDcz";

    @Test
    void shouldFindByUuid() {
        when(locationService.findLocationByUuid(any(UUID.class)))
                .thenReturn(testLocation());

        var result = locationFacade.findLocationByUuid(TEST_UUID);
        assertNotNull(result);
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(NAME, result.getName());
    }

    @Test
    void shouldFindAllLocation() {
        when(locationService.getLocations())
                .thenReturn(listOfLocation());

        var result = locationFacade.findAllLocation();
        assertNotNull(result);
        assertEquals(TEST_UUID, result.get(0).getUuid());
        assertEquals(NAME, result.get(0).getName());
    }

    @Test
    void shouldCreateLocation() {
        when(locationService.createLocation(any(CreateLocationInput.class)))
                .thenReturn(testLocation());

        var result = locationFacade.createLocation(testCreateLocationInput());
        assertNotNull(result);
        assertEquals(NAME, result.getName());
    }

    @Test
    void shouldUpdateLocation() {
        when(locationService.updateLocation(any(UpdateLocationInput.class), any(Location.class)))
                .thenReturn(testLocation());
        when(locationService.findLocationByUuid(any(UUID.class))).thenReturn(createLocation());

        var result = locationFacade.updateLocation(testUpdateLocationInput());
        assertNotNull(result);
        assertEquals(NAME, result.getName());
        assertEquals(TEST_UUID, result.getUuid());
    }

    private Location createLocation() {
        return Location
                .builder()
                .name(NAME)
                .uuid(TEST_UUID)
                .id(ID)
                .build();
    }

    private UpdateLocationInput testUpdateLocationInput() {
        return UpdateLocationInput.builder().uuid(TEST_UUID).name(NAME).build();
    }

    private CreateLocationInput testCreateLocationInput() {
        return CreateLocationInput.builder().name(NAME).build();
    }

    private List<Location> listOfLocation() {
        return List.of(testLocation());
    }

    private Location testLocation() {
        return Location.builder()
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }
}