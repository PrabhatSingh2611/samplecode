package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.impl.LocationFacade;
import digital.windmill.audra.graphql.resolver.location.LocationMutationResolver;
import digital.windmill.audra.graphql.type.location.CreateLocationInput;
import digital.windmill.audra.graphql.type.location.Location;
import digital.windmill.audra.graphql.type.location.UpdateLocationInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LocationMutationResolverTest {

    @Mock
    private LocationFacade locationFacade;

    @InjectMocks
    private LocationMutationResolver locationMutationResolver;

    @Test
    void shouldCreateLocation(@Mock CreateLocationInput input,
                              @Mock Location location) {

        when(locationFacade.createLocation(any(CreateLocationInput.class)))
                .thenReturn(location);

        var result = locationMutationResolver.createLocation(input);
        assertNotNull(result);
        assertSame(location, result.getLocation());
    }

    @Test
    void shouldUpdateLocation(@Mock UpdateLocationInput input,
                              @Mock Location location) {

        when(locationFacade.updateLocation(any(UpdateLocationInput.class)))
                .thenReturn(location);

        var result = locationMutationResolver.updateLocation(input);
        assertNotNull(result);
        assertSame(location, result.getLocation());
    }

}
