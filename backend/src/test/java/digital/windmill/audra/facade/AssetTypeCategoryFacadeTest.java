package digital.windmill.audra.facade;


import digital.windmill.audra.dao.entity.AssetTypeCategoryEntity;
import digital.windmill.audra.graphql.facade.impl.AssetTypeCategoryFacade;
import digital.windmill.audra.graphql.mapper.AssetTypeCategoryMapper;
import digital.windmill.audra.graphql.type.assetTypeCategory.AssetTypeCategoriesInput;
import digital.windmill.audra.graphql.type.assetTypeCategory.AssetTypeCategory;
import digital.windmill.audra.graphql.type.assetTypeCategory.CreateAssetTypeCategoryInput;
import digital.windmill.audra.graphql.type.assetTypeCategory.PatchAssetTypeCategoryInput;
import digital.windmill.audra.service.impl.AssetTypeCategoryService;
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
public class AssetTypeCategoryFacadeTest {

    private static final UUID CATEGORY_UUID = UUID.randomUUID();

    @Mock
    private AssetTypeCategoryService assetTypeCategoryService;

    @Mock
    private AssetTypeCategoryMapper assetTypeCategoryMapper;

    @InjectMocks
    private AssetTypeCategoryFacade assetTypeCategoryFacade;

    @Test
    void shouldFindAssetTypeCategories(@Mock AssetTypeCategoryEntity assetTypeCategoryEntity,
                                       @Mock AssetTypeCategory assetTypeCategory,
                                       @Mock AssetTypeCategoriesInput input) {

        when(assetTypeCategoryService.findAll(input)).thenReturn(createOneItemPage(assetTypeCategoryEntity));
        when(assetTypeCategoryMapper.map(assetTypeCategoryEntity)).thenReturn(assetTypeCategory);

        var actualResult = assetTypeCategoryFacade.getAssetTypeCategories(input);
        assertNotNull(actualResult);
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

    @Test
    void shouldCreateAssetTypeCategory(@Mock CreateAssetTypeCategoryInput input,
                                       @Mock AssetTypeCategoryEntity assetTypeCategoryEntity,
                                       @Mock AssetTypeCategory assetTypeCategory) {

        when(assetTypeCategoryMapper.map(input)).thenReturn(assetTypeCategoryEntity);
        when(assetTypeCategoryService.save(assetTypeCategoryEntity)).thenReturn(assetTypeCategoryEntity);
        when(assetTypeCategoryMapper.map(assetTypeCategoryEntity)).thenReturn(assetTypeCategory);

        var actualResult = assetTypeCategoryFacade.createAssetTypeCategory(input);
        assertEquals(assetTypeCategory, actualResult);
    }

    @Test
    void shouldPatchAssetTypeCategory(@Mock PatchAssetTypeCategoryInput input,
                                      @Mock AssetTypeCategoryEntity assetTypeCategoryEntity,
                                      @Mock AssetTypeCategory assetTypeCategory) {

        when(input.getId()).thenReturn(CATEGORY_UUID);
        when(assetTypeCategoryService.findAssetTypeCategoryByUuid(input.getId())).thenReturn(assetTypeCategoryEntity);
        when(assetTypeCategoryMapper.map(input, assetTypeCategoryEntity)).thenReturn(assetTypeCategoryEntity);
        when(assetTypeCategoryService.save(assetTypeCategoryEntity)).thenReturn(assetTypeCategoryEntity);
        when(assetTypeCategoryMapper.map(assetTypeCategoryEntity)).thenReturn(assetTypeCategory);

        var actualResult = assetTypeCategoryFacade.patchAssetTypeCategory(input);
        assertEquals(assetTypeCategory, actualResult);
    }

}
