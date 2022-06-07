package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.impl.AssetTypeFacade;
import digital.windmill.audra.graphql.resolver.assetType.AssetTypeResolver;
import digital.windmill.audra.graphql.type.assetType.AssetType;
import digital.windmill.audra.graphql.type.assetType.AssetTypeInput;
import digital.windmill.audra.graphql.type.assetType.AssetTypesInput;
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
public class AssetTypeResolverTest {

    private static final UUID ASSETTYPE_UUID = UUID.randomUUID();

    @Mock
    private AssetTypeFacade assetTypeFacade;

    @InjectMocks
    AssetTypeResolver assetTypeResolver;

    @Test
    void shouldGetAssetTypeByUuid(@Mock AssetTypeInput assetTypeInput,
                                  @Mock AssetType assetType) {

        when(assetTypeInput.getId()).thenReturn(ASSETTYPE_UUID);
        when(assetTypeFacade.findAssetTypeByUuid(any(UUID.class)))
                .thenReturn(assetType);

        var result = assetTypeResolver.assetType(assetTypeInput);
        assertNotNull(result);
        assertSame(assetType, result.getAssetType());
    }

    @Test
    void shouldGetAllAssetTypes(@Mock AssetType assetType,
                                @Mock AssetTypesInput input) {

        Page<AssetType> pagedResponse = createOneItemPage(assetType);
        when(assetTypeFacade.getAssetTypes(input)).thenReturn(pagedResponse);

        var result = assetTypeResolver.getAssetTypes(input);
        assertNotNull(result);
        assertEquals(pagedResponse.getContent(), result.getItems());
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

}
