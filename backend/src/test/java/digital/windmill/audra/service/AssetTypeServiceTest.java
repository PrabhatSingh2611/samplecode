package digital.windmill.audra.service;


import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.dao.repository.AssetTypeRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.AssetTypeMapper;
import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.service.impl.AssetTypeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetTypeServiceTest {
    @Mock
    private AssetTypeRepository assetTypeRepository;

    @Mock
    private AssetTypeMapper assetTypeMapper;

    @InjectMocks
    private AssetTypeServiceImpl assetTypeService;

    private static final Long ID = 1l;
    private static final UUID TEST_UUID = UUID.fromString("5478b586-e607-4448-ac05-3e5f2adbbc1b");
    private static final String TITLE = "Laptops";
    private static final String ICON = "https://google.com/laptops";

    @Test
    void shouldFindAssetByUuid(){
        when(assetTypeMapper.mapAssetTypeEntityToAssetType(any(AssetTypeEntity.class))).thenReturn(createAssetType());
        when(assetTypeRepository.findByUuid(any(UUID.class))).thenReturn(createAssetTypeEntity());
        var result = assetTypeService.findAssetByUuid(TEST_UUID);
        assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getUuid());
        Assertions.assertEquals(TITLE, result.getTitle());
        Assertions.assertEquals(ICON, result.getIcon());
    }


    @Test
    void shouldGetAssetsType(){
        when(assetTypeMapper.mapAssetTypeEntityToAssetType(any(AssetTypeEntity.class))).thenReturn(createAssetType());
        when(assetTypeRepository.findAll()).thenReturn(createAssetsTypeEntity());

        var actual = assetTypeService.getAssetsType();
        List<AssetType> expected = createAssetsType();
        assertNotNull(actual);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void shouldThrowDataNotFoundWhenUuidIsNull() {
        when(assetTypeRepository.findByUuid(any(UUID.class)))
                .thenThrow(new DataNotFoundException("location not found for :" + TEST_UUID));
        Assertions.assertThrows(DataNotFoundException.class, () -> assetTypeService.findAssetByUuid(TEST_UUID));
    }

    private List<AssetType> createAssetsType() {
        return Arrays.asList(createAssetType(),
                AssetType.builder()
                        .uuid(TEST_UUID)
                        .title(TITLE)
                        .icon(ICON)
                        .build());
    }

    private Optional<AssetTypeEntity> createAssetTypeEntity() {
        return Optional.ofNullable(AssetTypeEntity.builder()
                .uuid(TEST_UUID)
                .icon(ICON)
                .id(ID)
                .title(TITLE)
                .build());
    }



    private List<AssetTypeEntity> createAssetsTypeEntity() {
        return Arrays.asList(AssetTypeEntity.builder()
                        .uuid(TEST_UUID)
                        .title(TITLE)
                        .icon(ICON)
                        .build(),
                AssetTypeEntity.builder()
                        .uuid(TEST_UUID)
                        .title(TITLE)
                        .icon(ICON)
                        .build()
        );
    }

    private AssetType createAssetType() {
        return AssetType.builder()
                .uuid(TEST_UUID)
                .title(TITLE)
                .icon(ICON)
                .build();
    }
}
