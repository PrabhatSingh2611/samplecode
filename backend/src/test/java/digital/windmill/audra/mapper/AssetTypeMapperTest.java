package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.graphql.mapper.AssetTypeMapperImpl;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.input.AssetTypeInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class AssetTypeMapperTest {

    @InjectMocks
    private AssetTypeMapperImpl mapper;

    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String TITLE = "FZJIh4SB";
    private static final String ICON = "sQPXFD";
    private static final Long ID = 200L;
    private static final String ASSET_SERIAL_NUMBER = "40aab8f6";
    private static final EmployeePosition POSITION = new EmployeePosition();
    private static final String LOCATION = "Location";
    private static final String ROLE = "Admin";
    private final static Instant LOCAL_DATE = Instant.now();
    private final static ZonedDateTime DATE_TIME = ZonedDateTime.now();

    @Test

    void shouldMapAssetTypeEntityToAssetType() {
        var result = mapper.mapAssetTypeEntityToAssetType(createAssetTypeEntity());
        assertNotNull(result);
        assertAll(
                ()->assertEquals(TEST_UUID, result.getUuid()),
                ()->assertEquals(ICON, result.getIcon()),
                ()->assertEquals(ICON, result.getTitle())

        );
    }

    @Test
    void mapAssetTypeInputToAssetTypeEntity() {
        var result = mapper.mapAssetTypeInputToAssetTypeEntity(createAssetTypeInput());
        assertNotNull(result);
        assertAll(
                ()->assertEquals(TITLE, result.getTitle())
        );
    }

    private AssetTypeInput createAssetTypeInput() {
        return AssetTypeInput.builder()
                .uuid(TEST_UUID)
                .title(TITLE)
                .build();
    }

    private AssetTypeEntity createAssetTypeEntity() {
        return AssetTypeEntity.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .title(ICON)
                .icon(ICON)
                .build();
    }
}