package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.AssetTypeCategoryEntity;
import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.graphql.mapper.AssetTypeCategoryMapper;
import digital.windmill.audra.graphql.mapper.AssetTypeMapper;
import digital.windmill.audra.graphql.mapper.AssetTypeMapperImpl;
import digital.windmill.audra.graphql.mapper.DateTimeMapper;
import digital.windmill.audra.graphql.type.assetType.CreateAssetTypeInput;
import digital.windmill.audra.graphql.type.assetType.UpdateAssetTypeInput;
import digital.windmill.audra.graphql.type.assetTypeCategory.AssetTypeCategory;
import digital.windmill.audra.graphql.type.input.NodeInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AssetTypeMapperTest {

    private static final String ASSETTYPE_NAME = "Laptops";
    private static final String CATEGORY_NAME = "Electronics";
    private static final String ICON_NAME = "windows_10";
    private static final UUID ASSETTYPE_UUID = UUID.randomUUID();
    private static final UUID CATEGORY_UUID = UUID.randomUUID();
    private static final ZonedDateTime ZONE_DATE_TIME = ZonedDateTime.now();
    private static final Instant INSTANT_LOCAL_DATE = ZONE_DATE_TIME.toInstant();


    @Mock
    private AssetTypeCategoryMapper assetTypeCategoryMapper;

    @Mock
    private DateTimeMapper dateTimeMapper;

    @InjectMocks
    private AssetTypeMapperImpl assetTypeMapper;

    @Test
    void mapAssetTypeEntityToAssetType() {
        when(dateTimeMapper.map(any(Instant.class))).thenReturn(ZONE_DATE_TIME);
        when(assetTypeCategoryMapper.map(any(AssetTypeCategoryEntity.class))).thenReturn(createAssetTypeCategory());
        var result = assetTypeMapper.map(createAssetTypeEntity());

        Assertions.assertNotNull(result);
        assertEquals(ASSETTYPE_UUID, result.getId());
        assertEquals(ASSETTYPE_NAME, result.getName());
        assertEquals(ICON_NAME, result.getIconName());
        assertEquals(ZONE_DATE_TIME, result.getCreatedAt());
    }

    @Test
    void mapCreateAssetTypeInputToAssetTypeEntity() {
        var result = assetTypeMapper.map(buildCreateAssetTypeInput());

        Assertions.assertNotNull(result);
        assertEquals(ASSETTYPE_NAME, result.getName());
        assertEquals(ICON_NAME, result.getIconName());
    }

    @Test
    void mapUpdateAssetTypeInputToAssetTypeEntity() {
        var assetType = createAssetTypeEntity();
        var result = assetTypeMapper.map(buildUpdateAssetTypeInput(), assetType);

        Assertions.assertNotNull(result);
        assertEquals(ASSETTYPE_NAME, result.getName());
        assertEquals(ICON_NAME, result.getIconName());

    }

    private CreateAssetTypeInput buildCreateAssetTypeInput() {
        return CreateAssetTypeInput
                .builder()
                .category(NodeInput.of(CATEGORY_UUID))
                .name(ASSETTYPE_NAME)
                .iconName(ICON_NAME)
                .build();
    }

    private UpdateAssetTypeInput buildUpdateAssetTypeInput() {
        return UpdateAssetTypeInput
                .builder()
                .category(NodeInput.of(CATEGORY_UUID))
                .name(ASSETTYPE_NAME)
                .iconName(ICON_NAME)
                .build();
    }

    private AssetTypeEntity createAssetTypeEntity() {
        return AssetTypeEntity
                .builder()
                .uuid(ASSETTYPE_UUID)
                .category(createAssetTypeCategoryEntity())
                .name(ASSETTYPE_NAME)
                .iconName(ICON_NAME)
                .createdAt(INSTANT_LOCAL_DATE)
                .build();
    }

    private AssetTypeCategory createAssetTypeCategory() {
        return AssetTypeCategory
                .builder()
                .id(CATEGORY_UUID)
                .name(CATEGORY_NAME)
                .build();

    }

    private AssetTypeCategoryEntity createAssetTypeCategoryEntity() {
        return AssetTypeCategoryEntity
                .builder()
                .name(CATEGORY_NAME)
                .uuid(CATEGORY_UUID)
                .build();
    }


}


