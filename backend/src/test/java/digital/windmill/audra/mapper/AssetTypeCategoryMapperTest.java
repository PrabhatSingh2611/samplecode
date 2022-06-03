package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.AssetTypeCategoryEntity;
import digital.windmill.audra.graphql.mapper.AssetTypeCategoryMapperImpl;
import digital.windmill.audra.graphql.type.assetTypeCategory.CreateAssetTypeCategoryInput;
import digital.windmill.audra.graphql.type.assetTypeCategory.PatchAssetTypeCategoryInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class AssetTypeCategoryMapperTest {

    private static final UUID CATEGORY_UUID = UUID.randomUUID();
    private static final String CATEGORY_NAME = "furnitures";

    @InjectMocks
    private AssetTypeCategoryMapperImpl assetTypeCategoryMapper;

    @Test
    void mapAssetTypeCategoryEntityToAssetTypeCategory() {
        var result = assetTypeCategoryMapper.map(createAssetTypeCategoryEntity());

        assertNotNull(result);
        assertEquals(CATEGORY_UUID, result.getId());
        assertEquals(CATEGORY_NAME, result.getName());
    }

    @Test
    void mapCreateAssetTypeCategoryInputToAssetTypeCategoryEntity() {
        var category = createAssetTypeCategoryEntity();
        var result = assetTypeCategoryMapper.map(buildCreateAssetTypeCategoryInput());

        assertNotNull(result);
        assertEquals(CATEGORY_NAME, result.getName());
    }

    @Test
    void mapPatchAssetTypeCategoryInputToAssetTypeCategoryEntity() {
        var category = createAssetTypeCategoryEntity();
        var result = assetTypeCategoryMapper.map(buildPatchAssetTypeCategoryInput(), category);

        assertNotNull(result);
        assertEquals(CATEGORY_NAME, result.getName());
    }

    private CreateAssetTypeCategoryInput buildCreateAssetTypeCategoryInput() {
        return CreateAssetTypeCategoryInput
                .builder()
                .name(CATEGORY_NAME)
                .build();
    }


    private PatchAssetTypeCategoryInput buildPatchAssetTypeCategoryInput() {
        return PatchAssetTypeCategoryInput
                .builder()
                .name(CATEGORY_NAME)
                .build();
    }

    private AssetTypeCategoryEntity createAssetTypeCategoryEntity() {
        return AssetTypeCategoryEntity
                .builder()
                .uuid(CATEGORY_UUID)
                .name(CATEGORY_NAME)
                .build();
    }
}