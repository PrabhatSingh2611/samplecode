package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.LocationCountryEntity;
import digital.windmill.audra.graphql.facade.impl.LocationCountryFacade;
import digital.windmill.audra.graphql.mapper.LocationCountryMapper;
import digital.windmill.audra.graphql.type.locationCountry.CreateLocationCountryInput;
import digital.windmill.audra.graphql.type.locationCountry.LocationCountriesInput;
import digital.windmill.audra.graphql.type.locationCountry.LocationCountry;
import digital.windmill.audra.graphql.type.locationCountry.PatchLocationCountryInput;
import digital.windmill.audra.service.impl.LocationCountryService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationCountryFacadeTest {

    private static final UUID COUNTRY_UUID = UUID.randomUUID();

    @Mock
    private LocationCountryService locationCountryService;
    @Mock
    private LocationCountryMapper locationCountryMapper;

    @InjectMocks
    private LocationCountryFacade locationCountryFacade;

    @Test
    void shouldFindLocationCountries(@Mock LocationCountryEntity locationCountryEntity,
                                     @Mock LocationCountry locationCountry,
                                     @Mock LocationCountriesInput input) {
        when(locationCountryService.findAll(input)).thenReturn(createOneItemPage(locationCountryEntity));
        when(locationCountryMapper.map(locationCountryEntity)).thenReturn(locationCountry);

        var actualResult = locationCountryFacade.findLocationCountries(input);

        assertNotNull(actualResult);
    }

    @Test
    void shouldCreateLocationCountry(@Mock CreateLocationCountryInput input,
                                     @Mock LocationCountryEntity locationCountryEntity,
                                     @Mock LocationCountry locationCountry) {
        when(locationCountryMapper.map(input)).thenReturn(locationCountryEntity);
        when(locationCountryService.save(locationCountryEntity)).thenReturn(locationCountryEntity);
        when(locationCountryMapper.map(locationCountryEntity)).thenReturn(locationCountry);

        var actualResult = locationCountryFacade.createLocationCountry(input);

        assertEquals(locationCountry, actualResult);
    }

    @Test
    void shouldPatchLocationCountry(@Mock PatchLocationCountryInput input,
                                    @Mock LocationCountryEntity locationCountryEntity,
                                    @Mock LocationCountry locationCountry) {
        when(input.getId()).thenReturn(COUNTRY_UUID);
        when(locationCountryService.findByUuid(input.getId())).thenReturn(locationCountryEntity);
        when(locationCountryMapper.map(input, locationCountryEntity)).thenReturn(locationCountryEntity);
        when(locationCountryService.save(locationCountryEntity)).thenReturn(locationCountryEntity);
        when(locationCountryMapper.map(locationCountryEntity)).thenReturn(locationCountry);

        var actualResult = locationCountryFacade.patchLocationCountry(input);

        assertEquals(locationCountry, actualResult);
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }
}