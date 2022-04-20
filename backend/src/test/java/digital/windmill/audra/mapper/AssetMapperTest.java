package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.graphql.mapper.AssetMapperImpl;
import digital.windmill.audra.graphql.mapper.DateTimeMapper;
import digital.windmill.audra.graphql.mapper.EmployeeMapperImpl;
import digital.windmill.audra.graphql.type.Asset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
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

    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String ASSET_TITLE = "Asset title";
    private static final String ASSET_SERIAL_NUMBER = "40aab8f6";
    private static final String NAME = "Name";
    private final static Instant LOCAL_DATE = Instant.now();


    @Test
        //TODO: mapLocationEntityToLocation dates and employee
    void shouldMapAssetEntityToAsset() {
        Asset actual = mapper.mapAssetEntityToAsset(createAssetEntity());
        assertAll(
                () -> assertEquals(ASSET_TITLE, actual.getTitle()),
                () -> assertEquals(TEST_UUID, actual.getUuid()),
                () -> assertEquals(ASSET_SERIAL_NUMBER, actual.getSerial())
        );
    }


    private AssetEntity createAssetEntity() {
        AssetEntity a = new AssetEntity();
        a.setId(1L);
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
        e.setId(1L);
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
        p.setId(1L);
        p.setName(NAME);
        return p;
    }

    private LocationEntity createLocationEntity() {
        LocationEntity l = new LocationEntity();
        l.setId(1L);
        l.setName(NAME);
        return l;
    }
}
