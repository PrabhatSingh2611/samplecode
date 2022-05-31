package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.impl.LocationCountryFacade;
import digital.windmill.audra.graphql.resolver.locationCountry.LocationCountryQueryResolver;
import digital.windmill.audra.graphql.type.locationCountry.LocationCountriesInput;
import digital.windmill.audra.graphql.type.locationCountry.LocationCountry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LocationCountryQueryResolverTest {

    @Mock
    private LocationCountryFacade facade;

    @InjectMocks
    private LocationCountryQueryResolver resolver;

    @Test
    void shouldGetAllLocations(@Mock LocationCountry locationCountry, @Mock LocationCountriesInput input) {
        var pagedResponse = createOneItemPage(locationCountry);
        when(facade.findLocationCountries(input)).thenReturn(pagedResponse);

        var result = resolver.locationCountries(input);

        assertNotNull(result);
        assertEquals(pagedResponse.getContent(), result.getItems());
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

}
