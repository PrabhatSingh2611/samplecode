package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.AssetTypeFacade;
import digital.windmill.audra.graphql.resolver.asset.AssetTypeMutationResolver;
import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.graphql.type.input.CreateAssetTypeInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetTypeMutationResolverTest {

    private static final UUID TEST_UUID = UUID.randomUUID();
    private static final String TITLE = "chair";
    private static final String ICON = "https://google.com/chair";

    @Mock
    private AssetTypeFacade facade;

    @InjectMocks
    private AssetTypeMutationResolver assetTypeMutationResolver;

    @Test
    void shouldCreateAssetType(@Mock CreateAssetTypeInput input) {
        when(facade.createAssetType(any(CreateAssetTypeInput.class)))
                .thenReturn(createAssetType());

        var result = assetTypeMutationResolver.createAssetType(input);

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getItem().getUuid());
        assertEquals(TITLE, result.getItem().getTitle());
        assertEquals(ICON, result.getItem().getIcon());

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
