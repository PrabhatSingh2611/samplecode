package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.AssetFacade;
import digital.windmill.audra.graphql.resolver.asset.AssetMutationResolver;
import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.graphql.type.input.ArchiveAssetInput;
import digital.windmill.audra.graphql.type.input.CreateAssetInput;
import digital.windmill.audra.graphql.type.input.UpdateAssetInput;
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
public class AssetMutationTest {

    @Mock
    private AssetFacade assetFacade;

    @InjectMocks
    private AssetMutationResolver assetMutationResolver;

    @Test
    void shouldCreateAsset(@Mock CreateAssetInput createAssetInput, @Mock Asset asset) {
        when(assetFacade.createAsset(any(CreateAssetInput.class))).thenReturn(asset);

        var actualResult = assetMutationResolver.createAsset(createAssetInput);
        assertNotNull(actualResult);
        assertSame(asset, actualResult.getAsset());
    }

    @Test
    void shouldUpdateAsset(@Mock UpdateAssetInput updateAssetInput, @Mock Asset asset) {
        when(assetFacade.updateAsset(any(UpdateAssetInput.class))).thenReturn(asset);

        var actualResult = assetMutationResolver.updateAsset(updateAssetInput);
        assertSame(asset, actualResult.getAsset());
    }

    @Test
    void shouldArchiveAsset(@Mock ArchiveAssetInput archiveAssetInput, @Mock Asset asset) {
        when(assetFacade.updateAssetAsArchive(any(ArchiveAssetInput.class))).thenReturn(asset);
        var actualResult = assetMutationResolver.archiveAsset(archiveAssetInput);
        assertSame(asset, actualResult.getAsset());
    }
}
