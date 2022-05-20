package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.ObjectiveEntity;
import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.dao.entity.enums.ObjectiveStatus;
import digital.windmill.audra.graphql.mapper.DateTimeMapper;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.mapper.EmployeePositionMapper;
import digital.windmill.audra.graphql.mapper.ObjectiveMapperImpl;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.UpdateObjectiveInput;
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
class ObjectiveMapperTest {

    private static final Long ID = 1L;
    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String NAME = "Name";
    private static final String COMMENT = "schema,etc";
    private static final String DESCRIPTION = "Description";
    private static final ObjectiveStatus STATUS = ObjectiveStatus.NEW;
    private static final EmployeeRole ROLE = EmployeeRole.EMPLOYEE;
    private static final EmployeeRole EMPLOYEE_ROLE = EmployeeRole.EMPLOYEE;
    private static final ZonedDateTime ZONE_DATE_TIME = ZonedDateTime.now();
    private static final Instant INSTANT_LOCAL_DATE = ZONE_DATE_TIME.toInstant();
    @Mock
    EmployeeMapper employeeMapper;
    @Mock
    DateTimeMapper dateTimeMapper;
    @Mock
    EmployeePositionMapper employeePositionMapper;
    @InjectMocks
    private ObjectiveMapperImpl mapper;

    @Test
    void shouldMapObjectiveInputToEntity() {

        var result = mapper.mapObjectiveInputToEntity(
                testCreateObjectiveInput(), createEmployeeEntity());

        assertAll(
                () -> assertEquals(DESCRIPTION, result.getDescription()),
                () -> assertEquals(NAME, result.getName()),
                () -> assertEquals(STATUS, result.getStatus()),
                () -> assertEquals(COMMENT, result.getComments()),
                () -> assertEquals(INSTANT_LOCAL_DATE, result.getDueToDate()),
                () -> assertEquals(TEST_UUID, result.getEmployee().getUuid())
        );
    }

    @Test
    void shouldMapObjectiveEntityToObjective() {

        when(dateTimeMapper.map(any(Instant.class))).thenReturn(ZONE_DATE_TIME);
        var result = mapper.mapObjectiveEntityToObjective(createObjectiveEntity());

        assertAll(
                () -> assertEquals(DESCRIPTION, result.getDescription()),
                () -> assertEquals(NAME, result.getName()),
                () -> assertEquals(STATUS, result.getStatus()),
                () -> assertEquals(COMMENT, result.getComments()),
                () -> assertEquals(TEST_UUID, result.getEmployee().getId()),
                () -> assertEquals(ZONE_DATE_TIME, result.getDueToDate())
        );
    }

    @Test
    void shouldMapInputToEntityWhenUpdate() {


        var result = mapper.mapInputToEntityWhenUpdate(
                createUpdateObjectiveEntity(),
                createObjectiveEntity(),
                createEmployeeEntity()
        );

        assertAll(
                () -> assertEquals(DESCRIPTION, result.getDescription()),
                () -> assertEquals(NAME, result.getName()),
                () -> assertEquals(STATUS, result.getStatus()),
                () -> assertEquals(COMMENT, result.getComments()),
                () -> assertEquals(TEST_UUID, result.getEmployee().getUuid()),
                () -> assertEquals(INSTANT_LOCAL_DATE, result.getDueToDate())
        );
    }

    private UpdateObjectiveInput createUpdateObjectiveEntity() {
        return UpdateObjectiveInput.builder()
                .id(TEST_UUID)
                .name(NAME)
                .comments(COMMENT)
                .description(DESCRIPTION)
                .dueToDate(ZONE_DATE_TIME)
                .status(STATUS)
                .build();

    }

    private EmployeeEntity createEmployeeEntity() {
        EmployeeEntity e = new EmployeeEntity();
        e.setId(ID);
        e.setUuid(TEST_UUID);
        e.setFirstName(NAME);
        e.setLastName(NAME);
        e.setBirthday(INSTANT_LOCAL_DATE);
        e.setRole(EMPLOYEE_ROLE);

        return e;
    }

    private ObjectiveEntity createObjectiveEntity() {
        return ObjectiveEntity.builder()
                .uuid(UUID.randomUUID())
                .name(NAME)
                .comments(COMMENT)
                .description(DESCRIPTION)
                .dueToDate(INSTANT_LOCAL_DATE)
                .employee(createEmployeeEntity())
                .status(ObjectiveStatus.NEW)
                .build();
    }

    private CreateObjectiveInput testCreateObjectiveInput() {
        return CreateObjectiveInput.builder()
                .name(NAME)
                .comments(COMMENT)
                .description(DESCRIPTION)
                .dueToDate(ZONE_DATE_TIME)
                .employee(TEST_UUID)
                .status(ObjectiveStatus.NEW)
                .build();
    }

    private Objective createObjective() {
        return Objective.builder()
                .id(TEST_UUID)
                .name(NAME)
                .description(DESCRIPTION)
                .comments(COMMENT)
                .dueToDate(ZONE_DATE_TIME)
                .employee(createEmployee())
                .status(STATUS)
                .build();
    }

    private Employee createEmployee() {
        return Employee.builder()
                .id(TEST_UUID)
                .position(createEmployeePosition())
                .role(ROLE)
                .birthday(ZONE_DATE_TIME)
                .firstName(NAME)
                .lastName(NAME)
                .reportingManager(createReportingManager())
                .location(createLocation())
                .build();
    }

    private Location createLocation() {
        return Location.builder()
                .id(TEST_UUID)
                .country(NAME)
                .build();
    }

    private Employee createReportingManager() {
        return new Employee();
    }

    private EmployeePosition createEmployeePosition() {
        return EmployeePosition.builder()
                .id(TEST_UUID)
                .name(NAME)
                .build();
    }
}
