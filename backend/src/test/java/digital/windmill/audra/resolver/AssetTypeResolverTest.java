package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.AssetTypeFacade;
import digital.windmill.audra.graphql.resolver.asset.AssetTypeResolver;
import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.graphql.type.input.AssetTypeInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetTypeResolverTest {

    private static final UUID TEST_UUID = UUID.randomUUID();
    private static final String TITLE = "chair";
    private static final String ICON = "https://google.com/chair";

    @Mock
    private AssetTypeFacade facade;

    @InjectMocks
    AssetTypeResolver assetTypeResolver;

    @Test
    void shouldGetAssetTypeByUuid() {
        when(facade.findAssetTypeByUuid(any(UUID.class)))
                .thenReturn(createAssetType());

        var result = assetTypeResolver.assetType(createAssetTypeInput());

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getItem().getUuid());
        assertEquals(TITLE, result.getItem().getTitle());
        assertEquals(ICON, result.getItem().getIcon());

    }

    @Test
    void shouldGetAllAssetTypes() {
        when(facade.getAssetsType()).thenReturn(createAssetTypeList());

        var result = assetTypeResolver.getAssetTypes();
        Assertions.assertTrue(!result.getItems().isEmpty());
    }


    private AssetTypeInput createAssetTypeInput() {
        return AssetTypeInput
                .builder()
                .uuid(TEST_UUID)
                .title(TITLE)
                .build();
    }

    private List<AssetType> createAssetTypeList() {
        List<AssetType> assetTypesList = new ArrayList<>();
        assetTypesList.add(createAssetType());
        return assetTypesList;
    }

    private AssetType createAssetType() {
        return AssetType
                .builder()
                .uuid(TEST_UUID)
                .title(TITLE)
                .icon(ICON)
                .build();

    }
}
