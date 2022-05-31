package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.LocationCountryEntity;
import digital.windmill.audra.dao.repository.LocationCountryRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.locationCountry.LocationCountriesInput;
import digital.windmill.audra.service.impl.LocationCountryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class LocationCountryServiceTest {

    private static final UUID COUNTRY_UUID = UUID.randomUUID();

    @Mock
    private LocationCountryRepository locationCountryRepository;

    @InjectMocks
    private LocationCountryService locationCountryService;

    @Test
    void shouldReturnLocationCountryByUuid(@Mock LocationCountryEntity locationCountryEntity) {
        when(locationCountryRepository.findByUuid(any(UUID.class)))
                .thenReturn(Optional.ofNullable(locationCountryEntity));

        var result = locationCountryService.findByUuid(COUNTRY_UUID);

        assertNotNull(result);
        assertEquals(locationCountryEntity, result);
    }

    @Test
    void shouldSaveLocationCountry(@Mock LocationCountryEntity locationCountryEntity) {
        when(locationCountryRepository.save(locationCountryEntity))
                .thenReturn(locationCountryEntity);

        var result = locationCountryService.save(locationCountryEntity);
        assertNotNull(result);
        assertSame(locationCountryEntity, result);
    }

    @Test
    void shouldReturnAllLocations(@Mock LocationCountryEntity locationCountryEntity,
                                  @Mock LocationCountriesInput locationCountriesInput) {
        var locationPage = createOneItemPage(locationCountryEntity);
        when(locationCountryRepository.findAll((Specification<LocationCountryEntity>) any(), any(PageRequest.class)))
                .thenReturn(locationPage);
        var result = locationCountryService.findAll(locationCountriesInput);

        assertNotNull(result);
        assertSame(locationPage, result);
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

    @Test
    void shouldThrowDataNotFoundWhenUuidIsNull() {
        when(locationCountryRepository.findByUuid(any(UUID.class)))
                .thenThrow(new DataNotFoundException("Location country not found " + COUNTRY_UUID));
        Assertions.assertThrows(DataNotFoundException.class, () -> locationCountryService.findByUuid(COUNTRY_UUID));
    }

}
