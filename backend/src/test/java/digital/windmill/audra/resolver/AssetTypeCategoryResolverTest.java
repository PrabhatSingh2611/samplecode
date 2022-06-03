package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.impl.AssetTypeFacade;
import digital.windmill.audra.graphql.resolver.assetTypeCategory.AssetTypeCategoryResolver;
import digital.windmill.audra.graphql.type.assetType.AssetType;
import digital.windmill.audra.graphql.type.assetTypeCategory.AssetTypeCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetTypeCategoryResolverTest {

    private static final UUID CATEGORY_UUID = UUID.randomUUID();

    @Mock
    private AssetTypeFacade assetTypeFacade;

    @InjectMocks
    private AssetTypeCategoryResolver assetTypeCategoryResolver;

    @Test
    void shouldGetAssetTypesByCategory(@Mock AssetTypeCategory assetTypeCategory,
                                       @Mock AssetType assetType) {
        when(assetTypeCategory.getId()).thenReturn(CATEGORY_UUID);
        when(assetTypeFacade.findAssetTypesByCategoryUuid(CATEGORY_UUID)).thenReturn(List.of(assetType));

        var result = assetTypeCategoryResolver.types(assetTypeCategory);
        assertNotNull(result);
        assertEquals(List.of(assetType), result);
    }

}
