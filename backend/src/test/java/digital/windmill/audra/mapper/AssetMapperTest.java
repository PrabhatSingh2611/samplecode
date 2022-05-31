package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.graphql.mapper.AssetMapperImpl;
import digital.windmill.audra.graphql.mapper.AssetTypeMapperImpl;
import digital.windmill.audra.graphql.mapper.DateTimeMapper;
import digital.windmill.audra.graphql.mapper.EmployeeMapperImpl;
import digital.windmill.audra.graphql.mapper.LocationMapperImpl;
import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.graphql.type.input.ArchiveAssetInput;
import digital.windmill.audra.graphql.type.input.CreateAssetInput;
import digital.windmill.audra.graphql.type.input.NodeInput;
import digital.windmill.audra.graphql.type.input.UpdateAssetInput;
import digital.windmill.audra.graphql.type.location.Location;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetMapperTest {

    @InjectMocks
    private AssetMapperImpl mapper;

    @Mock
    private AssetTypeMapperImpl assetTypeMapper;
    @Mock
    private LocationMapperImpl locationMapper;
    @Mock
    private EmployeeMapperImpl employeeMapper;
    @Mock
    private DateTimeMapper dateTimeMapper;

    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String ASSET_TITLE = "Asset title";
    private static final String ASSET_SERIAL_NUMBER = "40aab8f6";
    private static final String NAME = "Name";
    private static final String ACTION_NAME = "update";
    private static final String COMMENT = "nothing";
    private static final String TAG_NUMBER = "323-45CXRE";
    private final static ZonedDateTime WAY_BILL_DATE = ZonedDateTime.now();
    private final static Instant LOCAL_DATE = Instant.now();
    private final static ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.now();

    private static final Long ID = 1L;


    @Test
    void shouldMapAssetEntityToAsset() {
        when(locationMapper.map(any(LocationEntity.class))).thenReturn(createLocation());
        when(assetTypeMapper.mapAssetTypeEntityToAssetType(any(AssetTypeEntity.class))).thenReturn(createAssetType());
        when(dateTimeMapper.map(any(Instant.class))).thenReturn(ZONED_DATE_TIME);
        var actual = mapper.mapAssetEntityToAsset(createAssetEntity());
        assertAll(
                () -> assertEquals(ASSET_TITLE, actual.getTitle()),
                () -> assertEquals(TEST_UUID, actual.getId()),
                () -> assertEquals(ASSET_SERIAL_NUMBER, actual.getSerialNumber()),
                () -> assertEquals(TEST_UUID, actual.getType().getId()),
                () -> assertEquals(TEST_UUID, actual.getLocation().getId()),
                () -> assertEquals(TAG_NUMBER, actual.getTagNumber()),
                () -> assertEquals(ZONED_DATE_TIME, actual.getWaybillDate()),
                () -> assertEquals(ZONED_DATE_TIME, actual.getArchivedDate())
        );
    }

    @Test
    void shouldMapAssetCreateInputToAssetEntity(@Mock LocationEntity locationEntity) {
        when(dateTimeMapper.map(any(ZonedDateTime.class))).thenReturn(LOCAL_DATE);
        var actual = mapper.mapAssetCreateInputToAssetEntity(createAssetInput(),
                createAssetTypeEntity(),
                createEmployeeEntity(),
                locationEntity);
        assertAll(
                () -> assertEquals(ASSET_TITLE, actual.getTitle()),
                () -> assertEquals(ASSET_SERIAL_NUMBER, actual.getSerialNumber()),
                () -> assertEquals(TEST_UUID, actual.getType().getUuid()),
                () -> assertEquals(NAME, actual.getType().getTitle()),
                () -> assertEquals(TEST_UUID, actual.getAssignee().getUuid()),
                () -> assertEquals(LOCAL_DATE, actual.getArchivedDate()),
                () -> assertEquals(ACTION_NAME, actual.getActionOnName()),
                () -> assertEquals(COMMENT, actual.getComment()),
                () -> assertEquals(TAG_NUMBER, actual.getTagNumber())
        );
    }

    @Test
    void shouldMapAssetUpdateInputToAssetEntity(@Mock LocationEntity locationEntity) {
        when(dateTimeMapper.map(any(ZonedDateTime.class))).thenReturn(LOCAL_DATE);
        var actual = mapper.mapAssetUpdateInputToAssetEntity(createAssetEntity(),updateAssetInput(),
                createAssetTypeEntity(),
                createEmployeeEntity(),
                locationEntity);
        assertAll(
                () -> assertEquals(TEST_UUID, actual.getUuid()),
                () -> assertEquals(ASSET_TITLE, actual.getTitle()),
                () -> assertEquals(ASSET_SERIAL_NUMBER, actual.getSerialNumber()),
                () -> assertEquals(TEST_UUID, actual.getType().getUuid()),
                () -> assertEquals(NAME, actual.getType().getTitle()),
                () -> assertEquals(TEST_UUID, actual.getAssignee().getUuid()),
                () -> assertEquals(LOCAL_DATE, actual.getArchivedDate()),
                () -> assertEquals(ACTION_NAME, actual.getActionOnName()),
                () -> assertEquals(COMMENT, actual.getComment()),
                () -> assertEquals(TAG_NUMBER, actual.getTagNumber())
        );
    }

    @Test
    void shouldMapArchiveAssetInputToAssetEntity() {

        var actual = mapper.mapArchiveAssetInputToAssetEntity(archiveAssetInput(),
                createAssetEntity());
        assertAll(
                () -> assertEquals(TEST_UUID, actual.getUuid()),
                () -> assertEquals(ASSET_TITLE, actual.getTitle()),
                () -> assertEquals(ASSET_SERIAL_NUMBER, actual.getSerialNumber()),
                () -> assertEquals(TEST_UUID, actual.getType().getUuid()),
                () -> assertEquals(NAME, actual.getType().getTitle()),
                () -> assertEquals(TEST_UUID, actual.getAssignee().getUuid()),
                () -> assertEquals(ACTION_NAME, actual.getActionOnName()),
                () -> assertEquals(COMMENT, actual.getComment()),
                () -> assertEquals(TAG_NUMBER, actual.getTagNumber())
        );
    }

    private ArchiveAssetInput archiveAssetInput() {
        return ArchiveAssetInput.builder()
                .asset(NodeInput.builder().id(TEST_UUID).build())
                .build();
    }

    private AssetTypeEntity createAssetTypeEntity() {
        return AssetTypeEntity.builder()
                .uuid(TEST_UUID)
                .id(ID)
                .title(NAME)
                .build();
    }

    private AssetType createAssetType() {
        return AssetType.builder()
                .id(TEST_UUID)
                .build();
    }

    private CreateAssetInput createAssetInput() {
        return CreateAssetInput
                .builder()
                .title(ASSET_TITLE)
                .serialNumber(ASSET_SERIAL_NUMBER)
                .actionOnName(ACTION_NAME)
                .assignee(NodeInput.builder().id(TEST_UUID).build())
                .type(NodeInput.builder().id(TEST_UUID).build())
                .archivedDate(ZONED_DATE_TIME)
                .waybillDate(ZONED_DATE_TIME)
                .nextActionDate(ZONED_DATE_TIME)
                .comment(COMMENT)
                .tagNumber(TAG_NUMBER)
                .build();
    }

    private UpdateAssetInput updateAssetInput() {
        return UpdateAssetInput
                .builder()
                .id(TEST_UUID)
                .title(ASSET_TITLE)
                .serialNumber(ASSET_SERIAL_NUMBER)
                .assignee(NodeInput.builder().id(TEST_UUID).build())
                .type(NodeInput.builder().id(TEST_UUID).build())
                .location(NodeInput.builder().id(TEST_UUID).build())
                .archivedDate(ZONED_DATE_TIME)
                .waybillDate(ZONED_DATE_TIME)
                .nextActionDate(ZONED_DATE_TIME)
                .actionOnName(ACTION_NAME)
                .comment(COMMENT)
                .build();
    }


    private AssetEntity createAssetEntity() {
        AssetEntity a = new AssetEntity();
        a.setId(ID);
        a.setUuid(TEST_UUID);
        a.setTitle(ASSET_TITLE);
        a.setType(AssetTypeEntity
                .builder()
                .uuid(TEST_UUID)
                .title(NAME)
                .build());
        a.setSerialNumber(ASSET_SERIAL_NUMBER);
        a.setLocation(createLocationEntity());
        a.setComment(COMMENT);
        a.setArchivedDate(LOCAL_DATE);
        a.setWaybillDate(LOCAL_DATE);
        a.setAssignee(createEmployeeEntity());
        a.setTagNumber(TAG_NUMBER);
        a.setNextActionDate(LOCAL_DATE);
        a.setActionOnName(ACTION_NAME);
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

    private Location createLocation() {
        return Location.builder()
                .id(TEST_UUID)
                .build();
    }
}
