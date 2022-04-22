package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.LocationFacade;
import digital.windmill.audra.graphql.resolver.location.LocationResolver;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.LocationInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LocationResolverTest {

    @Mock
    private LocationFacade facade;

    @InjectMocks
    private LocationResolver locationResolver;

    private static final UUID TEST_UUID = UUID.fromString("ab0829f1-1972-46b9-a01a-8e88f95552de");
    private static final String NAME = "1EYdby2";


    @Test
    void testLocation() {
        when(facade.findByUuid(any(UUID.class)))
                .thenReturn(createLocation());

        var result = locationResolver.location(createLocationInput());
        assertNotNull(result);
        assertEquals(TEST_UUID, result.getLocation().getUuid());
        assertEquals(NAME, result.getLocation().getName());

    }

    private LocationInput createLocationInput() {
        return LocationInput
                .builder()
                .uuid(TEST_UUID)
                .name(NAME)
                .build();

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
