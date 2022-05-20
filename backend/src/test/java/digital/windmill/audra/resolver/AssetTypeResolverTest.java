package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.AssetTypeFacade;
import digital.windmill.audra.graphql.resolver.asset.AssetTypeResolver;
import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.graphql.type.input.AssetTypeInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetTypeResolverTest {

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private AssetTypeFacade assetTypeFacade;

    @InjectMocks
    AssetTypeResolver assetTypeResolver;

    @Test
    void shouldGetAssetTypeByUuid(@Mock AssetTypeInput assetTypeInput,
                                  @Mock AssetType assetType) {

        when(assetTypeInput.getId()).thenReturn(TEST_UUID);
        when(assetTypeFacade.findAssetTypeByUuid(any(UUID.class)))
                .thenReturn(assetType);

        var result = assetTypeResolver.assetType(assetTypeInput);
        assertNotNull(result);
        assertSame(assetType, result.getItem());
    }

    @Test
    void shouldGetAllAssetTypes(@Mock AssetType assetType) {

        Page<AssetType> pagedResponse = createOneItemPage(assetType);
        when(assetTypeFacade.getAssetsType()).thenReturn(pagedResponse);

        var result = assetTypeResolver.getAssetTypes();
        assertNotNull(result);
        assertEquals(pagedResponse.getContent(), result.getItems());
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

}
