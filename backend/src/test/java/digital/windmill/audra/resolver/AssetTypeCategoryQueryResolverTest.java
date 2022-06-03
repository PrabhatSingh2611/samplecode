package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.impl.AssetTypeCategoryFacade;
import digital.windmill.audra.graphql.resolver.assetTypeCategory.AssetTypeCategoryQueryResolver;
import digital.windmill.audra.graphql.type.assetTypeCategory.AssetTypeCategoriesInput;
import digital.windmill.audra.graphql.type.assetTypeCategory.AssetTypeCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetTypeCategoryQueryResolverTest {

    @Mock
    private AssetTypeCategoryFacade assetTypeCategoryFacade;

    @InjectMocks
    private AssetTypeCategoryQueryResolver assetTypeCategoryQueryResolver;

    @Test
    void shouldGetAllAssetTypeCategories(@Mock AssetTypeCategory assetTypeCategory,
                                         @Mock AssetTypeCategoriesInput input) {

        Page<AssetTypeCategory> pagedResponse = createOneItemPage(assetTypeCategory);
        when(assetTypeCategoryFacade.getAssetTypeCategories(input)).thenReturn(pagedResponse);

        var result = assetTypeCategoryQueryResolver.getAssetTypeCategories(input);
        assertNotNull(result);
        assertEquals(pagedResponse.getContent(), result.getItems());
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

}
