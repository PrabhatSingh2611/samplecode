package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.impl.LocationFacade;
import digital.windmill.audra.graphql.resolver.locationCountry.LocationCountryResolver;
import digital.windmill.audra.graphql.type.location.Location;
import digital.windmill.audra.graphql.type.locationCountry.LocationCountry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LocationCountryResolverTest {

    private static final UUID LOCATION_COUNTRY_UUID = UUID.randomUUID();

    @Mock
    private LocationFacade facade;

    @InjectMocks
    private LocationCountryResolver resolver;

    @Test
    void shouldGetLocationsByCountry(@Mock LocationCountry locationCountry,
                                     @Mock Location location) {
        when(locationCountry.getId()).thenReturn(LOCATION_COUNTRY_UUID);
        when(facade.findLocationsByCountryUuid(LOCATION_COUNTRY_UUID)).thenReturn(List.of(location));

        var result = resolver.locations(locationCountry);
        assertNotNull(result);
        assertEquals(List.of(location), result);
    }

}
