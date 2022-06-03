package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.AssetTypeCategoryEntity;
import digital.windmill.audra.dao.repository.AssetTypeCategoryRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.assetTypeCategory.AssetTypeCategoriesInput;
import digital.windmill.audra.service.impl.AssetTypeCategoryService;
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
public class AssetTypeCategoryServiceTest {

    private static final UUID CATEGORY_UUID = UUID.randomUUID();

    @Mock
    private AssetTypeCategoryRepository assetTypeCategoryRepository;

    @InjectMocks
    private AssetTypeCategoryService assetTypeCategoryService;

    @Test
    void shouldReturnAssetTypeCategoryByUuid(@Mock AssetTypeCategoryEntity assetTypeCategoryEntity) {
        when(assetTypeCategoryRepository.findByUuid(any(UUID.class))).thenReturn(Optional.ofNullable(assetTypeCategoryEntity));

        var result = assetTypeCategoryService.findAssetTypeCategoryByUuid(CATEGORY_UUID);

        assertNotNull(result);
        assertEquals(assetTypeCategoryEntity, result);
    }

    @Test
    void shouldSaveAssetTypeCategory(@Mock AssetTypeCategoryEntity assetTypeCategoryEntity) {
        when(assetTypeCategoryRepository.save(assetTypeCategoryEntity)).thenReturn(assetTypeCategoryEntity);

        var result = assetTypeCategoryService.save(assetTypeCategoryEntity);
        assertNotNull(result);
        assertSame(assetTypeCategoryEntity, result);
    }

    @Test
    void shouldThrowDataNotFoundWhenUuidIsNull() {
        when(assetTypeCategoryRepository.findByUuid(any(UUID.class)))
                .thenThrow(new DataNotFoundException("Category not found " + CATEGORY_UUID));
        Assertions.assertThrows(DataNotFoundException.class, () -> assetTypeCategoryService.findAssetTypeCategoryByUuid(CATEGORY_UUID));
    }

    @Test
    void shouldReturnAllAssetTypeCategories(@Mock AssetTypeCategoryEntity assetTypeCategoryEntity,
                                            @Mock AssetTypeCategoriesInput input) {

        var categoryPage = createOneItemPage(assetTypeCategoryEntity);
        when(assetTypeCategoryRepository.findAll((Specification<AssetTypeCategoryEntity>) any(), any(PageRequest.class)))
                .thenReturn(categoryPage);
        var result = assetTypeCategoryService.findAll(input);

        assertNotNull(result);
        assertSame(categoryPage, result);
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

}
