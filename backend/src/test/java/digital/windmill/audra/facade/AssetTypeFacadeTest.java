package digital.windmill.audra.facade;


import digital.windmill.audra.graphql.facade.impl.AssetTypeFacadeImpl;
import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.service.impl.AssetTypeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetTypeFacadeTest {

    @Mock
    AssetTypeServiceImpl assetTypeServiceImpl;

    @InjectMocks
    AssetTypeFacadeImpl assetTypeFacadeImpl;

    private static final UUID TEST_UUID = UUID.fromString("5478b586-e607-4448-ac05-3e5f2adbbc1b");
    private static final UUID SECOND_UUID = UUID.fromString("b7c34a7d-eeb8-4491-b2c8-0e79d1367b6b");
    private static final String TITLE = "Laptops";
    private static final String ICON = "https://google.com/laptops";

    @Test
    void shouldFindAssetTypeByUuid() {
        when(assetTypeServiceImpl.findAssetByUuid(any(UUID.class))).thenReturn(createAssetType());
        var result = assetTypeFacadeImpl.findAssetTypeByUuid(TEST_UUID);
        assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getUuid());
        Assertions.assertEquals(TITLE, result.getTitle());
        Assertions.assertEquals(ICON, result.getIcon());
    }

    @Test
    void shouldGetAssetsType() {
        when(assetTypeServiceImpl.getAssetsType()).thenReturn(createAssetsType());
        var actual = assetTypeFacadeImpl.getAssetsType();
        List<AssetType> expected = createAssetsType();
        assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }

    private List<AssetType> createAssetsType() {
        return Arrays.asList(AssetType.builder()
                        .uuid(TEST_UUID)
                        .title(TITLE)
                        .icon(ICON)
                        .build(),
                AssetType.builder()
                        .uuid(SECOND_UUID)
                        .title(TITLE)
                        .icon(ICON)
                        .build()
        );
    }

    private AssetType createAssetType() {
        return AssetType.builder()
                .uuid(TEST_UUID)
                .title(TITLE)
                .icon(ICON)
                .build();
    }
}
