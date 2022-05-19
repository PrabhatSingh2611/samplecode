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
public class LocationResolverTest {

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private LocationFacade locationfacade;

    @InjectMocks
    private LocationResolver locationResolver;

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
    void shouldGetAllLocations(@Mock Location location){

        Page<Location> pagedResponse = createOneItemPage(location);
        when(locationfacade.getLocations()).thenReturn(pagedResponse);

        var result = locationResolver.locations();
        assertNotNull(result);
        assertEquals(pagedResponse.getContent(), result.getItems());
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

}
