package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.impl.LocationCountryFacade;
import digital.windmill.audra.graphql.resolver.locationCountry.LocationCountryMutationResolver;
import digital.windmill.audra.graphql.type.locationCountry.CreateLocationCountryInput;
import digital.windmill.audra.graphql.type.locationCountry.LocationCountry;
import digital.windmill.audra.graphql.type.locationCountry.PatchLocationCountryInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LocationCountryMutationResolverTest {

    @Mock
    private LocationCountryFacade facade;

    @InjectMocks
    private LocationCountryMutationResolver mutationResolver;

    @Test
    void shouldCreateLocationCountry(@Mock CreateLocationCountryInput input,
                                     @Mock LocationCountry locationCountry) {
        when(facade.createLocationCountry(input)).thenReturn(locationCountry);

        var result = mutationResolver.createLocationCountry(input);

        assertNotNull(result);
        assertSame(locationCountry, result.getLocationCountry());
    }

    @Test
    void shouldPatchLocationCountry(@Mock PatchLocationCountryInput input,
                                    @Mock LocationCountry locationCountry) {
        when(facade.patchLocationCountry(input)).thenReturn(locationCountry);

        var result = mutationResolver.patchLocationCountry(input);

        assertNotNull(result);
        assertSame(locationCountry, result.getLocationCountry());
    }

}
