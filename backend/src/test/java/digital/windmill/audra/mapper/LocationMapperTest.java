package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.LocationCountryEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.mapper.LocationCountryMapperImpl;
import digital.windmill.audra.graphql.mapper.LocationMapperImpl;
import digital.windmill.audra.graphql.type.input.NodeInput;
import digital.windmill.audra.graphql.type.location.CreateLocationInput;
import digital.windmill.audra.graphql.type.location.UpdateLocationInput;
import digital.windmill.audra.graphql.type.locationCountry.LocationCountry;
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
class LocationMapperTest {

    private static final String LOCATION_NAME = "Main office";
    private static final String COUNTRY_NAME = "Spain";
    private static final String COUNTRY_ICON = "spain_flag";
    private static final UUID LOCATION_UUID = UUID.randomUUID();
    private static final UUID COUNTRY_UUID = UUID.randomUUID();

    @Mock
    private LocationCountryMapperImpl locationCountryMapper;

    @InjectMocks
    private LocationMapperImpl mapper;

    @Test
    void mapLocationEntityToLocation() {
        when(locationCountryMapper.map(any(LocationCountryEntity.class))).thenReturn(buildLocationCountry());
        var result = mapper.map(createLocationEntity());

        assertNotNull(result);
        assertEquals(LOCATION_UUID, result.getId());
        assertEquals(LOCATION_NAME, result.getName());
        assertEquals(COUNTRY_UUID, result.getCountry().getId());
        assertEquals(COUNTRY_NAME, result.getCountry().getName());
        assertEquals(COUNTRY_ICON, result.getCountry().getIconName());
    }

    @Test
    void mapCreateLocationInputToLocationEntity() {
        var country = createLocationCountryEntity();
        var result = mapper.map(buildCreateLocationInput(), country);

        assertNotNull(result);
        assertEquals(LOCATION_NAME, result.getName());
        assertEquals(COUNTRY_UUID, result.getCountry().getUuid());
    }

    @Test
    void mapUpdateLocationInputToLocationEntity() {
        var location = createLocationEntity();
        var country = createLocationCountryEntity();
        var result = mapper.map(buildUpdateLocationInput(), location, country);

        assertNotNull(result);
        assertEquals(LOCATION_NAME, result.getName());
        assertEquals(COUNTRY_UUID, result.getCountry().getUuid());
    }

    private CreateLocationInput buildCreateLocationInput() {
        return CreateLocationInput.builder()
                .name(LOCATION_NAME)
                .country(NodeInput.of(COUNTRY_UUID))
                .build();
    }

    private UpdateLocationInput buildUpdateLocationInput() {
        return UpdateLocationInput.builder()
                .name(LOCATION_NAME)
                .country(NodeInput.of(COUNTRY_UUID))
                .build();
    }

    private LocationEntity createLocationEntity() {
        return LocationEntity.builder()
                .uuid(LOCATION_UUID)
                .name(LOCATION_NAME)
                .country(createLocationCountryEntity())
                .build();
    }

    private LocationCountryEntity createLocationCountryEntity() {
        return LocationCountryEntity.builder()
                .uuid(COUNTRY_UUID)
                .name(COUNTRY_NAME)
                .iconName(COUNTRY_ICON)
                .build();
    }

    private LocationCountry buildLocationCountry() {
        return LocationCountry.builder()
                .id(COUNTRY_UUID)
                .name(COUNTRY_NAME)
                .iconName(COUNTRY_ICON)
                .build();
    }
}