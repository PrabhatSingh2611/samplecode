package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.impl.LocationFacadeImpl;
import digital.windmill.audra.graphql.resolver.location.LocationMutationResolver;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import digital.windmill.audra.graphql.type.input.UpdateLocationInput;
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
public class LocationMutationResolverTest {

    private static final UUID TEST_UUID = UUID.randomUUID();
    private static final String NAME = "h1H633Pl";

    @Mock
    private LocationFacadeImpl locationFacade;

    @InjectMocks
    private LocationMutationResolver locationMutationResolver;

    @Test
    void shouldCreateLocation(@Mock CreateLocationInput input) {
        when(locationFacade.createLocation(any(CreateLocationInput.class)))
                .thenReturn(createLocation());

        var result = locationMutationResolver.createLocation(input);

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getLocation().getUuid());
        assertEquals(NAME, result.getLocation().getName());
    }

    @Test
    void shouldUpdateLocation(@Mock UpdateLocationInput input) {
        when(locationFacade.updateLocation(any(UpdateLocationInput.class)))
                .thenReturn(createLocation());

        var result = locationMutationResolver.updateLocation(input);

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getLocation().getUuid());
        assertEquals(NAME, result.getLocation().getName());
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
