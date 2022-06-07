package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.impl.LocationFacade;
import digital.windmill.audra.graphql.resolver.location.LocationQueryResolver;
import digital.windmill.audra.graphql.type.location.Location;
import digital.windmill.audra.graphql.type.location.LocationInput;
import digital.windmill.audra.graphql.type.location.LocationsInput;
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
public class LocationQueryResolverTest {

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private LocationFacade locationfacade;

    @InjectMocks
    private LocationQueryResolver locationResolver;

    @Test
    void shouldGetLocationByUuid(@Mock LocationInput input,
                                 @Mock Location location) {

        when(input.getId()).thenReturn(TEST_UUID);
        when(locationfacade.findLocationByUuid(any(UUID.class)))
                .thenReturn(location);

        var result = locationResolver.location(input);
        assertNotNull(result);
        assertSame(location, result.getLocation());
    }

    @Test
    void shouldGetAllLocations(@Mock Location location, @Mock LocationsInput locationsInput) {

        Page<Location> pagedResponse = createOneItemPage(location);
        when(locationfacade.getLocations(locationsInput)).thenReturn(pagedResponse);

        var result = locationResolver.locations(locationsInput);
        assertNotNull(result);
        assertEquals(pagedResponse.getContent(), result.getItems());
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

}
