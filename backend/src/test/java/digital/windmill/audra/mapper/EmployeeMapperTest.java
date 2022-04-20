package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.graphql.mapper.DateTimeMapper;
import digital.windmill.audra.graphql.mapper.EmployeeMapperImpl;
import digital.windmill.audra.graphql.mapper.EmployeePositionMapper;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static graphql.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeMapperTest {

    @InjectMocks
    private EmployeeMapperImpl mapper;

    @Mock
    DateTimeMapper dateTimeMapper;
    @Mock
    EmployeePositionMapper employeePositionMapper;

    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final Long ID = 22L;
    private static final String NAME = "9AMj3X";
    private static final EmployeeRole ROLE = EmployeeRole.ADMIN;
    private static final ZoneId zone = ZoneId.systemDefault();
    private final static ZonedDateTime DATE_TIME = ZonedDateTime.of(2020, 2, 12, 7, 55, 20, 100, zone);
    private final static Instant LOCAL_DATE = DATE_TIME.toInstant();

    @Test
    void shouldMapEmployeeEntityToEmployee() {
        when(employeePositionMapper.mapEmployeePositionEntityToEmployeePosition(any(EmployeePositionEntity.class)))
                .thenReturn(createPosition());
        when(dateTimeMapper.map(any(Instant.class))).thenReturn(DATE_TIME);

        var actual = mapper.mapEmployeeEntityToEmployee(createEmployeeEntity());
        assertAll(
                () -> assertEquals(TEST_UUID, actual.getUuid()),
                () -> assertEquals(NAME, actual.getFirstName()),
                () -> assertEquals(NAME, actual.getLastName()),
                () -> assertEquals(NAME, actual.getPosition().getName()),
                () -> assertEquals(NAME, actual.getLocation().getName()),
                () -> assertEquals(DATE_TIME, actual.getBirthday())
        );
    }

    @Test
    void shouldMapEmployeeInputToEmployeeEntity() {
        when(dateTimeMapper.map(any(ZonedDateTime.class))).thenReturn(LOCAL_DATE);
        var result = mapper.mapEmployeeInputToEmployeeEntity(
                createCreateEmployeeInput(),
                createEmployeeEntity(),
                createEmployeePositionEntity(),
                createLocationEntity());

        assertNotNull(result);
        assertAll(
                () -> assertEquals(NAME, result.getFirstName()),
                () -> assertEquals(NAME, result.getLocation().getName()),
                () -> assertEquals(NAME, result.getPosition().getName()),
                () -> assertEquals(LOCAL_DATE, result.getBirthday())

        );

    }

    private EmployeePosition createPosition() {
        return EmployeePosition.builder()
                .uuid(TEST_UUID)
                .id(ID)
                .name(NAME)
                .build();
    }

    private EmployeePositionEntity createEmployeePositionEntity() {
        return EmployeePositionEntity.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }

    private CreateEmployeeInput createCreateEmployeeInput() {
        return CreateEmployeeInput.builder()
                .location(TEST_UUID)
                .position(TEST_UUID)
                .reportingManager(TEST_UUID)
                .firstName(NAME)
                .lastName(NAME)
                .role(ROLE)
                .birthday(DATE_TIME)
                .build();
    }

    private EmployeeEntity createEmployeeEntity() {
        EmployeeEntity e = new EmployeeEntity();
        e.setUuid(TEST_UUID);
        e.setFirstName(NAME);
        e.setLastName(NAME);
        e.setBirthday(LOCAL_DATE);
        e.setId(ID);
        e.setPosition(createPositionEntity());
        e.setLocation(createLocationEntity());

        return e;

    }

    private LocationEntity createLocationEntity() {
        LocationEntity e = new LocationEntity();
        e.setId(ID);
        e.setName(NAME);
        return e;
    }

    private EmployeePositionEntity createPositionEntity() {
        return EmployeePositionEntity.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }
}
