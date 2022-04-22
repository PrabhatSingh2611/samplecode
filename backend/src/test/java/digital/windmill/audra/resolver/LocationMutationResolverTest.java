package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.impl.LocationFacadeImpl;
import digital.windmill.audra.graphql.resolver.location.LocationMutationResolver;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import digital.windmill.audra.graphql.type.input.UpdateLocationInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LocationMutationResolverTest {

    @Mock
    private LocationFacadeImpl locationFacade;
    @Mock
    private LocationMutationResolver locationMutationResolver;

    private static final UUID TEST_UUID = UUID.fromString("ab0829f1-1972-46b9-a01a-8e88f95552de");
    private static final String NAME = "h1H633Pl";

    @Test
    void shouldCreateLocation() {
        when(locationFacade.createLocation(any(CreateLocationInput.class)))
                .thenReturn(createLocation());
        var result = locationMutationResolver.createLocation(createLocationInput());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getLocation().getUuid());
        Assertions.assertEquals(NAME, result.getLocation().getName());
    }

    @Test
    void shouldUpdateLocation() {
        when(locationFacade.updateLocation(any(UpdateLocationInput.class)))
                .thenReturn(createLocation());
        var result = locationMutationResolver.updateLocation(updateLocationInput());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getLocation().getUuid());
        Assertions.assertEquals(NAME, result.getLocation().getName());
    }

    private CreateLocationInput createLocationInput() {
        return CreateLocationInput
                .builder()
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

    private UpdateLocationInput updateLocationInput() {
        return UpdateLocationInput
                .builder()
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }

}
