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

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetTypeResolverTest {

    @Mock
    private AssetTypeFacade facade;

    @InjectMocks
    AssetTypeResolver assetTypeResolver;

    private static final UUID TEST_UUID = UUID.fromString("ab0829f1-1972-46b9-a01a-8e88f95552de");
    private static final String TITLE = "chair";
    private static final String ICON = "https://google.com/chair";


    @Test
    void testAssetType() {
        when(facade.findAssetTypeByUuid(any(UUID.class)))
                .thenReturn(createAssetType());

        var result = assetTypeResolver.assetType(createAssetTypeInput());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getItem().getUuid());
        Assertions.assertEquals(TITLE, result.getItem().getTitle());
        Assertions.assertEquals(ICON, result.getItem().getIcon());

    }

    private AssetTypeInput createAssetTypeInput() {
        return AssetTypeInput
                .builder()
                .uuid(TEST_UUID)
                .title(TITLE)
                .build();
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
