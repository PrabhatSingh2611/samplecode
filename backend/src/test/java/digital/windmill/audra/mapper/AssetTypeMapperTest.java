package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.graphql.mapper.AssetTypeMapperImpl;
import digital.windmill.audra.graphql.type.input.CreateAssetTypeInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static graphql.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class AssetTypeMapperTest {

    @InjectMocks
    private AssetTypeMapperImpl mapper;

    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String TITLE = "FZJIh4SB";
    private static final String ICON = "sQPXFD";
    private static final Long ID = 200L;

    @Test
    void shouldMapAssetTypeEntityToAssetType() {
        var result = mapper.mapAssetTypeEntityToAssetType(createAssetTypeEntity());
        assertNotNull(result);
        assertAll(
                () -> assertEquals(TEST_UUID, result.getUuid()),
                () -> assertEquals(ICON, result.getIcon()),
                () -> assertEquals(ICON, result.getTitle())

        );
    }

    @Test
    void mapAssetTypeInputToAssetTypeEntity() {
        var result = mapper.mapAssetTypeInputToAssetTypeEntity(createAssetTypeInput());
        assertNotNull(result);
        assertAll(
                () -> assertEquals(TITLE, result.getTitle())
        );
    }

    private CreateAssetTypeInput createAssetTypeInput() {
        return CreateAssetTypeInput.builder()
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