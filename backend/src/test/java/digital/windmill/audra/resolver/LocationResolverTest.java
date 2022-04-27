package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.LocationFacade;
import digital.windmill.audra.graphql.resolver.location.LocationResolver;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.LocationInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LocationResolverTest {

    private static final UUID TEST_UUID = UUID.randomUUID();
    private static final String NAME = "1EYdby2";

    @Mock
    private LocationFacade facade;

    @InjectMocks
    private LocationResolver locationResolver;

    @Test
    void shouldGetLocationByUuid() {
        when(facade.findLocationByUuid(any(UUID.class)))
                .thenReturn(createLocation());

        var result = locationResolver.location(createLocationInput());

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getLocation().getUuid());
        assertEquals(NAME, result.getLocation().getName());

    }

    @Test
    void shouldGetAllLocations(){
        when(facade.findAllLocation()).thenReturn(createLocationList());

        var result = locationResolver.locations();

        assertNotNull(result);
        Assertions.assertTrue(!result.getItems().isEmpty());
    }

    private LocationInput createLocationInput() {
        return LocationInput
                .builder()
                .uuid(TEST_UUID)
                .build();

    }

    private List<Location> createLocationList() {
        List<Location> locationList = new ArrayList<>();
        locationList.add(createLocation());
        return locationList;
    }


    private Location createLocation() {
        return Location
                .builder()
                .id(1L)
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }

}
