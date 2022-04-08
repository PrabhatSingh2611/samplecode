package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.graphql.facade.AssetFacade;
import digital.windmill.audra.graphql.mapper.AssetMapper;
import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.service.AssetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetFacadeTest {

    @Mock
    private AssetService assetService;

    @Mock
    private AssetMapper assetMapper;

    @InjectMocks
    private AssetFacade facade;

    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String ASSET_TITLE = "Asset title";
    private static final String ASSET_SERIAL_NUMBER = "40aab8f6";
    private static final String NAME = "Name";
    private static final String POSITION = "Position";
    private static final String ROLE = "Admin";
    private final static Instant LOCAL_DATE = Instant.now();
    private final static ZonedDateTime DATE_TIME = ZonedDateTime.now();

    @Test
    void shouldReturnAllAssetById() {
        when(assetService.findByUuid(any(UUID.class))).thenReturn(createAssetEntity());
        when(assetMapper.map(any(AssetEntity.class))).thenReturn(createAsset());

        var result = facade.findAssetByUuid(TEST_UUID);

        assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getUuid());
        Assertions.assertEquals(ASSET_TITLE, result.getTitle());
        Assertions.assertEquals(NAME, result.getEmployee().getFirstName());
        Assertions.assertEquals(NAME, result.getEmployee().getLastName());
    }

    private Asset createAsset() {
        return Asset.builder()
                .uuid(TEST_UUID)
                .title(ASSET_TITLE)
                .serial(ASSET_SERIAL_NUMBER)
                .type(createAssetType())
                .employee(createEmployee())
                .archivedDate(DATE_TIME)
                .purchasedDate(DATE_TIME)
                .build();
    }

    private Employee createEmployee() {
        return Employee.builder()
                .uuid(TEST_UUID)
                .firstName(NAME)
                .lastName(NAME)
                .birthday(DATE_TIME)
                .location(createLocation())
                .role(ROLE)
                .build();
    }

    private AssetType createAssetType() {
        return AssetType.builder()
                .uuid(TEST_UUID)
                .title(ASSET_TITLE)
                .build();

    }

    private AssetEntity createAssetEntity() {
        AssetEntity a = new AssetEntity();
        a.setId(1L);
        a.setUuid(TEST_UUID);
        a.setTitle(ASSET_TITLE);
        a.setSerialNumber(ASSET_SERIAL_NUMBER);
        a.setArchivedDate(LOCAL_DATE);
        a.setPurchasedDate(LOCAL_DATE);
        return a;
    }

    private Location createLocation() {
        return Location.builder().id(1L).uuid(TEST_UUID).name(NAME).build();
    }
}
