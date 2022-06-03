package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.impl.AssetTypeFacade;
import digital.windmill.audra.graphql.resolver.assetType.AssetTypeMutationResolver;
import digital.windmill.audra.graphql.type.assetType.AssetType;
import digital.windmill.audra.graphql.type.assetType.CreateAssetTypeInput;
import digital.windmill.audra.graphql.type.assetType.UpdateAssetTypeInput;
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
public class AssetTypeMutationResolverTest {

    @Mock
    private AssetTypeFacade assetTypeFacade;

    @InjectMocks
    private AssetTypeMutationResolver assetTypeMutationResolver;

    @Test
    void shouldCreateAssetType(@Mock CreateAssetTypeInput input,
                               @Mock AssetType assetType) {

        when(assetTypeFacade.createAssetType(any(CreateAssetTypeInput.class))).thenReturn(assetType);

        var result = assetTypeMutationResolver.createAssetType(input);
        assertNotNull(result);
        assertSame(assetType, result.getAssetType());

    }

    @Test
    void shouldUpdateAssetType(@Mock UpdateAssetTypeInput input,
                               @Mock AssetType assetType) {

        when(assetTypeFacade.updateAssetType(any(UpdateAssetTypeInput.class))).thenReturn(assetType);

        var result = assetTypeMutationResolver.updateAssetType(input);
        assertNotNull(result);
        assertSame(assetType, result.getAssetType());

    }
}
