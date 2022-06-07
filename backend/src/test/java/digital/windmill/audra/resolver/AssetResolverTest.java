package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.AssetFacade;
import digital.windmill.audra.graphql.resolver.asset.AssetResolver;
import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.graphql.type.input.AssetInput;
import digital.windmill.audra.graphql.type.input.AssetsInput;
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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetResolverTest {


    private static final UUID ASSET_UUID = UUID.randomUUID();

    @Mock
    private AssetFacade assetFacade;

    @InjectMocks
    private AssetResolver assetResolver;

    @Test
    void shouldGetAssetByUuid(@Mock AssetInput input,
                              @Mock Asset asset) {

        when(input.getId()).thenReturn(ASSET_UUID);
        when(assetFacade.findAssetByUuid(any(UUID.class))).thenReturn(asset);

        var result = assetResolver.asset(input);
        assertNotNull(result);
        assertSame(asset, result.getAsset());
    }

    @Test
    void shouldGetAllAssets(@Mock AssetsInput input,
                            @Mock Asset asset) {

        Page<Asset> pagedResponse = createOneItemPage(asset);
        when(assetFacade.findAllAssets(input)).thenReturn(pagedResponse);

        var result = assetResolver.assets(input);
        assertNotNull(result);
        assertEquals(pagedResponse.getContent(), result.getItems());
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

}
