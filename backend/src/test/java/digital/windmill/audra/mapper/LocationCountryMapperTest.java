package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.LocationCountryEntity;
import digital.windmill.audra.graphql.mapper.LocationCountryMapperImpl;
import digital.windmill.audra.graphql.type.locationCountry.CreateLocationCountryInput;
import digital.windmill.audra.graphql.type.locationCountry.PatchLocationCountryInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class LocationCountryMapperTest {

    private static final String COUNTRY_NAME = "Spain";
    private static final String COUNTRY_ICON = "spain_flag";
    private static final UUID COUNTRY_UUID = UUID.randomUUID();

    @InjectMocks
    private LocationCountryMapperImpl mapper;

    @Test
    void mapLocationCountryEntityToLocationCountry() {
        var result = mapper.map(createLocationCountryEntity());

        assertNotNull(result);
        assertEquals(COUNTRY_UUID, result.getId());
        assertEquals(COUNTRY_NAME, result.getName());
        assertEquals(COUNTRY_ICON, result.getIconName());
    }

    @Test
    void mapCreateLocationCountryInputToLocationCountryEntity() {
        var country = createLocationCountryEntity();
        var result = mapper.map(buildCreateLocationCountryInput());

        assertNotNull(result);
        assertEquals(COUNTRY_NAME, result.getName());
        assertEquals(COUNTRY_ICON, result.getIconName());
    }

    @Test
    void mapPatchLocationCountryInputToLocationEntity() {
        var country = createLocationCountryEntity();
        var result = mapper.map(buildPatchLocationCountryInput(), country);

        assertNotNull(result);
        assertEquals(COUNTRY_NAME, result.getName());
        assertEquals(COUNTRY_ICON, result.getIconName());
    }

    private CreateLocationCountryInput buildCreateLocationCountryInput() {
        return CreateLocationCountryInput.builder()
                .name(COUNTRY_NAME)
                .iconName(COUNTRY_ICON)
                .build();
    }

    private PatchLocationCountryInput buildPatchLocationCountryInput() {
        return PatchLocationCountryInput.builder()
                .name(COUNTRY_NAME)
                .iconName(null)
                .build();
    }

    private LocationCountryEntity createLocationCountryEntity() {
        return LocationCountryEntity.builder()
                .uuid(COUNTRY_UUID)
                .name(COUNTRY_NAME)
                .iconName(COUNTRY_ICON)
                .build();
    }
}