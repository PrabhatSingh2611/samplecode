package digital.windmill.audra.facade;


import digital.windmill.audra.dao.entity.AssetTypeCategoryEntity;
import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.graphql.facade.impl.AssetTypeFacade;
import digital.windmill.audra.graphql.mapper.AssetTypeMapper;
import digital.windmill.audra.graphql.type.assetType.AssetType;
import digital.windmill.audra.graphql.type.assetType.AssetTypesInput;
import digital.windmill.audra.graphql.type.assetType.CreateAssetTypeInput;
import digital.windmill.audra.graphql.type.assetType.UpdateAssetTypeInput;
import digital.windmill.audra.graphql.type.input.NodeInput;
import digital.windmill.audra.service.impl.AssetTypeCategoryService;
import digital.windmill.audra.service.impl.AssetTypeService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetTypeFacadeTest {

    private static final UUID ASSET_TYPE_UUID = UUID.randomUUID();
    private static final UUID CATEGORY_UUID = UUID.randomUUID();

    @Mock
    private AssetTypeService assetTypeService;

    @Mock
    private AssetTypeCategoryService assetTypeCategoryService;

    @Mock
    private AssetTypeMapper assetTypeMapper;

    @InjectMocks
    private AssetTypeFacade assetTypeFacade;

    @Test
    void shouldFindAssetTypeByUuid(@Mock AssetTypeEntity assetTypeEntity,
                                   @Mock AssetType assetType) {

        when(assetTypeService.findAssetByUuid(ASSET_TYPE_UUID)).thenReturn(assetTypeEntity);
        when(assetTypeMapper.map(assetTypeEntity)).thenReturn(assetType);

        var actualResult = assetTypeFacade.findAssetTypeByUuid(ASSET_TYPE_UUID);
        assertEquals(assetType, actualResult);
    }

    @Test
    void shouldGetAssetsType(@Mock AssetTypesInput input,
                             @Mock AssetTypeEntity assetTypeEntity,
                             @Mock AssetType assetType) {

        when(assetTypeService.getAssetTypes(input)).thenReturn(createOneItemPage(assetTypeEntity));
        when(assetTypeMapper.map(assetTypeEntity)).thenReturn(assetType);

        var actualResult = assetTypeFacade.getAssetTypes(input);
        assertEquals(createOneItemPage(assetType), actualResult);
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

    @Test
    void shouldCreateAssetType(@Mock CreateAssetTypeInput input,
                               @Mock AssetTypeCategoryEntity assetTypeCategoryEntity,
                               @Mock AssetTypeEntity assetTypeEntity,
                               @Mock AssetType assetType) {

        when(input.getCategory()).thenReturn(NodeInput.of(String.valueOf(CATEGORY_UUID)));
        when(assetTypeCategoryService.findAssetTypeCategoryByUuid(input.getCategory().getId())).thenReturn(assetTypeCategoryEntity);
        when(assetTypeMapper.map(input)).thenReturn(assetTypeEntity);
        when(assetTypeService.save(assetTypeEntity)).thenReturn(assetTypeEntity);
        when(assetTypeMapper.map(assetTypeEntity)).thenReturn(assetType);

        var actualResult = assetTypeFacade.createAssetType(input);
        assertEquals(assetType, actualResult);
    }

    @Test
    void shouldCreateAssetTypeWithoutCategory(@Mock CreateAssetTypeInput input,
                                              @Mock AssetTypeEntity assetTypeEntity,
                                              @Mock AssetType assetType) {

        when(input.getCategory()).thenReturn(null);
        when(assetTypeMapper.map(input)).thenReturn(assetTypeEntity);
        when(assetTypeService.save(assetTypeEntity)).thenReturn(assetTypeEntity);
        when(assetTypeMapper.map(assetTypeEntity)).thenReturn(assetType);

        var actualResult = assetTypeFacade.createAssetType(input);
        assertEquals(assetType, actualResult);
    }

    @Test
    void shouldUpdateAssetType(@Mock UpdateAssetTypeInput input,
                               @Mock AssetTypeEntity assetTypeEntity,
                               @Mock AssetTypeCategoryEntity assetTypeCategoryEntity,
                               @Mock AssetType assetType) {

        when(input.getCategory()).thenReturn(NodeInput.of(String.valueOf(CATEGORY_UUID)));
        when(assetTypeCategoryService.findAssetTypeCategoryByUuid(input.getCategory().getId())).thenReturn(assetTypeCategoryEntity);
        when(assetTypeService.findAssetByUuid(input.getId())).thenReturn(assetTypeEntity);
        when(assetTypeMapper.map(input, assetTypeEntity)).thenReturn(assetTypeEntity);
        when(assetTypeService.save(assetTypeEntity)).thenReturn(assetTypeEntity);
        when(assetTypeMapper.map(assetTypeEntity)).thenReturn(assetType);

        var actualResult = assetTypeFacade.updateAssetType(input);
        assertEquals(assetType, actualResult);
    }

    @Test
    void shouldUpdateAssetTypeWithoutCategory(@Mock UpdateAssetTypeInput input,
                                              @Mock AssetTypeEntity assetTypeEntity,
                                              @Mock AssetType assetType) {

        when(input.getCategory()).thenReturn(null);
        when(assetTypeService.findAssetByUuid(input.getId())).thenReturn(assetTypeEntity);
        when(assetTypeMapper.map(input, assetTypeEntity)).thenReturn(assetTypeEntity);
        when(assetTypeService.save(assetTypeEntity)).thenReturn(assetTypeEntity);
        when(assetTypeMapper.map(assetTypeEntity)).thenReturn(assetType);

        var actualResult = assetTypeFacade.updateAssetType(input);
        assertEquals(assetType, actualResult);
    }

}
