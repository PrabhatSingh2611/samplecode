package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.graphql.mapper.AssetMapperImpl;
import digital.windmill.audra.graphql.mapper.AssetTypeMapper;
import digital.windmill.audra.graphql.mapper.DateTimeMapper;
import digital.windmill.audra.graphql.mapper.EmployeeMapperImpl;
import digital.windmill.audra.graphql.type.input.CreateAssetInput;
import digital.windmill.audra.graphql.type.input.UpdateAssetInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AssetMapperTest {

    @InjectMocks
    private AssetMapperImpl mapper;

    @Mock
    private EmployeeMapperImpl employeeMapper;
    @Mock
    private DateTimeMapper zonedDateTime;
    @Mock
    private AssetTypeMapper assetTypeMapper;

    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String ASSET_TITLE = "Asset title";
    private static final String ASSET_SERIAL_NUMBER = "40aab8f6";
    private static final String NAME = "Name";
    private final static Instant LOCAL_DATE = Instant.now();
    private final static ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.now();

    private static final Long ID = 1L;


    @Test
        //TODO: mapLocationEntityToLocation dates and employee
    void shouldMapAssetEntityToAsset() {
        var actual = mapper.mapAssetEntityToAsset(createAssetEntity());
        assertAll(
                () -> assertEquals(ASSET_TITLE, actual.getTitle()),
                () -> assertEquals(TEST_UUID, actual.getId()),
                () -> assertEquals(ASSET_SERIAL_NUMBER, actual.getSerial())
        );
    }

    @Test
    void shouldMapAssetCreateInputToAssetEntity() {
        var actual = mapper.mapAssetCreateInputToAssetEntity(createAssetInput(),
                createAssetTypeEntity(),
                createEmployeeEntity());
        assertAll(
                () -> assertEquals(ASSET_TITLE, actual.getTitle()),
                () -> assertEquals(ASSET_SERIAL_NUMBER, actual.getSerialNumber()),
                () -> assertEquals(TEST_UUID, actual.getType().getUuid()),
                () -> assertEquals(NAME, actual.getType().getTitle()),
                () -> assertEquals(TEST_UUID, actual.getEmployee().getUuid())
        );
    }

    @Test
    void shouldMapAssetUpdateInputToAssetEntity() {
        var actual = mapper.mapAssetUpdateInputToAssetEntity(updateAssetInput(),
                createAssetEntity(),
                createAssetTypeEntity(),
                createEmployeeEntity());
        assertAll(
                () -> assertEquals(TEST_UUID, actual.getUuid()),
                () -> assertEquals(ASSET_TITLE, actual.getTitle()),
                () -> assertEquals(ASSET_SERIAL_NUMBER, actual.getSerialNumber()),
                () -> assertEquals(TEST_UUID, actual.getType().getUuid()),
                () -> assertEquals(NAME, actual.getType().getTitle()),
                () -> assertEquals(TEST_UUID, actual.getEmployee().getUuid())
        );
    }

    private AssetTypeEntity createAssetTypeEntity() {
        return AssetTypeEntity.builder()
                .uuid(TEST_UUID)
                .id(ID)
                .title(NAME)
                .build();
    }

    private CreateAssetInput createAssetInput() {
        return CreateAssetInput
                .builder()
                .title(ASSET_TITLE)
                .serial(ASSET_SERIAL_NUMBER)
                .employee(TEST_UUID)
                .type(TEST_UUID)
                .archivedDate(ZONED_DATE_TIME)
                .purchasedDate(ZONED_DATE_TIME)
                .nextActionDate(ZONED_DATE_TIME)
                .build();
    }

    private UpdateAssetInput updateAssetInput() {
        return UpdateAssetInput
                .builder()
                .id(TEST_UUID)
                .title(ASSET_TITLE)
                .serial(ASSET_SERIAL_NUMBER)
                .employee(TEST_UUID)
                .type(TEST_UUID)
                .archivedDate(ZONED_DATE_TIME)
                .purchasedDate(ZONED_DATE_TIME)
                .nextActionDate(ZONED_DATE_TIME)
                .build();
    }


    private AssetEntity createAssetEntity() {
        AssetEntity a = new AssetEntity();
        a.setId(ID);
        a.setUuid(TEST_UUID);
        a.setTitle(ASSET_TITLE);
        a.setSerialNumber(ASSET_SERIAL_NUMBER);
        a.setArchivedDate(LOCAL_DATE);
        a.setPurchasedDate(LOCAL_DATE);
        a.setEmployee(createEmployeeEntity());
        return a;
    }

    private EmployeeEntity createEmployeeEntity() {
        EmployeeEntity e = new EmployeeEntity();
        e.setId(ID);
        e.setFirstName(NAME);
        e.setLastName(NAME);
        e.setUuid(TEST_UUID);
        e.setRole(EmployeeRole.EMPLOYEE);
        e.setLocation(createLocationEntity());
        e.setPosition(createPositionEntity());
        return e;
    }

    private EmployeePositionEntity createPositionEntity() {
        EmployeePositionEntity p = new EmployeePositionEntity();
        p.setId(ID);
        p.setName(NAME);
        return p;
    }

    private LocationEntity createLocationEntity() {
        LocationEntity l = new LocationEntity();
        l.setId(ID);
        l.setName(NAME);
        return l;
    }
}
