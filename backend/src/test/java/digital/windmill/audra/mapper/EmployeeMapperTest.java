package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.graphql.mapper.DateTimeMapper;
import digital.windmill.audra.graphql.mapper.EmployeeMapperImpl;
import digital.windmill.audra.graphql.mapper.EmployeePositionMapper;
import digital.windmill.audra.graphql.mapper.LocationMapper;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import digital.windmill.audra.graphql.type.input.UpdateEmployeeInput;
import digital.windmill.audra.graphql.type.location.Location;
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
    @Mock
    LocationMapper locationMapper;

    private static final UUID EMPLOYEE_UUID = UUID.randomUUID();
    private static final UUID LOCATION_UUID = UUID.randomUUID();
    private static final UUID POSITION_UUID = UUID.randomUUID();
    private static final UUID REPORTING_MANAGER_UUID = UUID.randomUUID();
    private static final String NAME = "name";
    private static final String FIRST_NAME = "first";
    private static final String LAST_NAME = "last";
    private static final EmployeeRole ROLE = EmployeeRole.ADMIN;
    private static final ZoneId ZONE_ID = ZoneId.systemDefault();
    private final static ZonedDateTime BIRTH_DATE = ZonedDateTime.of(2020, 2, 12, 7, 55, 20, 100, ZONE_ID);
    private final static Instant BIRTH_INSTANT = BIRTH_DATE.toInstant();

    @Test
    void shouldMapEmployeeEntityToEmployee() {
        when(employeePositionMapper.mapEmployeePositionEntityToEmployeePosition(any(EmployeePositionEntity.class)))
                .thenReturn(buildPosition());
        when(locationMapper.map(any(LocationEntity.class)))
                .thenReturn(buildLocation());
        when(dateTimeMapper.map(any(Instant.class))).thenReturn(BIRTH_DATE);

        var actual = mapper.mapEmployeeEntityToEmployee(buildEmployeeEntity());
        assertAll(
                () -> assertEquals(EMPLOYEE_UUID, actual.getId()),
                () -> assertEquals(NAME, actual.getFirstName()),
                () -> assertEquals(NAME, actual.getLastName()),
                () -> assertEquals(BIRTH_DATE, actual.getBirthday()),
                () -> assertEquals(POSITION_UUID, actual.getPosition().getId()),
                () -> assertEquals(LOCATION_UUID, actual.getLocation().getId())
        );
    }

    @Test
    void shouldMapEmployeeInputToEmployeeEntity() {
        when(dateTimeMapper.map(any(ZonedDateTime.class))).thenReturn(BIRTH_INSTANT);
        var result = mapper.mapEmployeeInputToEmployeeEntity(
                buildCreateEmployeeInput(),
                buildEmployeeEntity(),
                buildEmployeePositionEntity(),
                buildLocationEntity());

        assertNotNull(result);
        assertAll(
                () -> assertEquals(FIRST_NAME, result.getFirstName()),
                () -> assertEquals(LAST_NAME, result.getLastName()),
                () -> assertEquals(ROLE, result.getRole()),
                () -> assertEquals(BIRTH_INSTANT, result.getBirthday()),
                () -> assertEquals(LOCATION_UUID, result.getLocation().getUuid()),
                () -> assertEquals(POSITION_UUID, result.getPosition().getUuid())
        );

    }

    @Test
    void shouldMapUpdateEmployeeInputToEmployeeEntity() {
        when(dateTimeMapper.map(any(ZonedDateTime.class))).thenReturn(BIRTH_INSTANT);
        var result = mapper.mapUpdateEmployeeInputToEmployeeEntity(
                buildUpdateEmployeeInput(),
                buildEmployeeEntity(),
                buildEmployeeEntity(),
                buildEmployeePositionEntity(),
                buildLocationEntity());

        assertNotNull(result);
        assertAll(
                () -> assertEquals(EMPLOYEE_UUID, result.getUuid()),
                () -> assertEquals(FIRST_NAME, result.getFirstName()),
                () -> assertEquals(LAST_NAME, result.getLastName()),
                () -> assertEquals(ROLE, result.getRole()),
                () -> assertEquals(BIRTH_INSTANT, result.getBirthday()),
                () -> assertEquals(LOCATION_UUID, result.getLocation().getUuid()),
                () -> assertEquals(POSITION_UUID, result.getPosition().getUuid())
        );

    }

    private EmployeePosition buildPosition() {
        return EmployeePosition.builder()
                .id(POSITION_UUID)
                .build();
    }

    private EmployeePositionEntity buildEmployeePositionEntity() {
        return EmployeePositionEntity.builder()
                .uuid(POSITION_UUID)
                .build();
    }

    private CreateEmployeeInput buildCreateEmployeeInput() {
        return CreateEmployeeInput.builder()
                .location(LOCATION_UUID)
                .position(POSITION_UUID)
                .reportingManager(REPORTING_MANAGER_UUID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .role(ROLE)
                .birthday(BIRTH_DATE)
                .build();
    }

    private UpdateEmployeeInput buildUpdateEmployeeInput() {
        return UpdateEmployeeInput.builder()
                .location(LOCATION_UUID)
                .position(POSITION_UUID)
                .reportingManager(REPORTING_MANAGER_UUID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .role(ROLE)
                .birthday(BIRTH_DATE)
                .build();
    }

    private EmployeeEntity buildEmployeeEntity() {
        EmployeeEntity e = new EmployeeEntity();
        e.setUuid(EMPLOYEE_UUID);
        e.setFirstName(NAME);
        e.setLastName(NAME);
        e.setBirthday(BIRTH_INSTANT);
        e.setPosition(buildPositionEntity());
        e.setLocation(buildLocationEntity());
        return e;
    }

    private Location buildLocation() {
        return Location.builder()
                .id(LOCATION_UUID)
                .build();
    }

    private LocationEntity buildLocationEntity() {
        return LocationEntity.builder()
                .uuid(LOCATION_UUID)
                .build();
    }

    private EmployeePositionEntity buildPositionEntity() {
        return EmployeePositionEntity.builder()
                .uuid(POSITION_UUID)
                .build();
    }
}
