package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.impl.AssetTypeCategoryFacade;
import digital.windmill.audra.graphql.resolver.assetTypeCategory.AssetTypeCategoryMutationResolver;
import digital.windmill.audra.graphql.type.assetTypeCategory.AssetTypeCategory;
import digital.windmill.audra.graphql.type.assetTypeCategory.CreateAssetTypeCategoryInput;
import digital.windmill.audra.graphql.type.assetTypeCategory.PatchAssetTypeCategoryInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetTypeCategoryMutationResolverTest {

    @Mock
    private AssetTypeCategoryFacade assetTypeCategoryFacade;

    @InjectMocks
    private AssetTypeCategoryMutationResolver assetTypeCategoryMutationResolver;

    @Test
    void shouldCreateAssetTypeCategory(@Mock CreateAssetTypeCategoryInput input,
                                       @Mock AssetTypeCategory assetTypeCategory) {

        when(assetTypeCategoryFacade.createAssetTypeCategory(any(CreateAssetTypeCategoryInput.class))).thenReturn(assetTypeCategory);

        var result = assetTypeCategoryMutationResolver.createAssetTypeCategory(input);
        assertNotNull(result);
        assertSame(assetTypeCategory, result.getAssetTypeCategory());

    }

    @Test
    void shouldUpdateAssetType(@Mock PatchAssetTypeCategoryInput input,
                               @Mock AssetTypeCategory assetTypeCategory) {

        when(assetTypeCategoryFacade.patchAssetTypeCategory(any(PatchAssetTypeCategoryInput.class))).thenReturn(assetTypeCategory);

        var result = assetTypeCategoryMutationResolver.patchAssetTypeCategory(input);
        assertNotNull(result);
        assertSame(assetTypeCategory, result.getAssetTypeCategory());

    }
}
