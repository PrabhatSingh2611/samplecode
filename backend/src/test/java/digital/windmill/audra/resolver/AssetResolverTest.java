package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.AssetFacade;
import digital.windmill.audra.graphql.resolver.asset.AssetResolver;
import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.AssetInput;
import digital.windmill.audra.graphql.type.input.AssetsInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.PageImpl;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetResolverTest {

    @Mock
    private AssetFacade facade;

    @InjectMocks
    private AssetResolver assetResolver;

    private static final UUID TEST_UUID = UUID.fromString("54720899-db8b-472f-a36a-008e2f2f3b57");
    private static final String ASSET_TITLE = "Title";
    private static final String ASSET_SERIAL_NUMBER = "40abh2f6";
    private static final String NAME = "Name";
    private static final String ROLE = "Admin";
    private final static ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.now();


    @Test
    void testAsset() {
        when(facade.findAssetByUuid(any(UUID.class)))
                .thenReturn(createAsset());

        var result = assetResolver.asset(createAssetInput());
        assertNotNull(result);
        assertEquals(TEST_UUID, result.getItem().getUuid());
        assertEquals(ASSET_TITLE, result.getItem().getTitle());
        assertEquals(ASSET_SERIAL_NUMBER, result.getItem().getSerial());
        assertEquals(NAME, result.getItem().getEmployee().getFirstName());
        assertEquals(NAME, result.getItem().getEmployee().getLastName());
    }

    @Test
    void testAssets(@Mock AssetsInput input) {
        List<Asset> assets = List.of(createAsset());
        var pagedResponse = new PageImpl(assets);
        when(facade.findAllAssets(any(AssetsInput.class))).thenReturn(pagedResponse);
        var result = assetResolver.assets(input);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getItems().get(0).getUuid());
        Assertions.assertEquals(NAME, result.getItems().get(0).getEmployee().getFirstName());
        Assertions.assertEquals(NAME, result.getItems().get(0).getEmployee().getLastName());
        Assertions.assertEquals(ASSET_TITLE, result.getItems().get(0).getTitle());
        Assertions.assertEquals(ASSET_SERIAL_NUMBER, result.getItems().get(0).getSerial());
        Assertions.assertEquals(TEST_UUID, result.getItems().get(0).getUuid());
        Assertions.assertEquals(ZONED_DATE_TIME, result.getItems().get(0).getEmployee().getBirthday());
        Assertions.assertEquals(ROLE, result.getItems().get(0).getEmployee().getRole());
    }

    private AssetInput createAssetInput() {
        return AssetInput
                .builder()
                .uuid(TEST_UUID)
                .build();
    }

    private Asset createAsset() {
        return Asset
                .builder()
                .uuid(TEST_UUID)
                .title(ASSET_TITLE)
                .type(createAssetType())
                .serial(ASSET_SERIAL_NUMBER)
                .employee(createEmployee())
                .archivedDate(ZONED_DATE_TIME)
                .purchasedDate(ZONED_DATE_TIME)
                .build();
    }

    private Employee createEmployee() {
        return Employee.builder()
                .id(1L)
                .uuid(TEST_UUID)
                .firstName(NAME)
                .lastName(NAME)
                .birthday(ZONED_DATE_TIME)
                .location(createLocation())
                .role(ROLE)
                .build();
    }

    private Location createLocation() {
        return Location.builder()
                .uuid(TEST_UUID)
                .name(NAME)
                .build();

    }

    private AssetType createAssetType() {
        return AssetType.builder()
                .uuid(TEST_UUID)
                .title(ASSET_TITLE)
                .build();
    }

}
