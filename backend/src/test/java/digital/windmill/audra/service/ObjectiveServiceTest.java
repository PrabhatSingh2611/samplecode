package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.entity.ObjectiveEntity;
import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.dao.entity.enums.ObjectiveStatus;
import digital.windmill.audra.dao.repository.ObjectiveRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.mapper.ObjectiveMapper;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.EmployeeObjectiveInput;
import digital.windmill.audra.graphql.type.input.UpdateObjectiveInput;
import digital.windmill.audra.service.impl.ObjectiveServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ObjectiveServiceTest {

    @Mock
    ObjectiveRepository objectiveRepository;
    @Mock
    ObjectiveMapper objectiveMapper;
    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    ObjectiveServiceImpl objectiveService;

    private static final Long ID = 1l;
    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String NAME = "Name";
    private static final String COMMENT = "schema,etc";
    private static final String DESCRIPTION = "Description";
    private static final ObjectiveStatus STATUS = ObjectiveStatus.NEW;
    private static final String ROLE = "EMPLOYEE";
    private static final EmployeeRole EMPLOYEE_ROLE = EmployeeRole.EMPLOYEE;
    private final static ZonedDateTime ZONE_DATE_TIME = ZonedDateTime.now();
    private final static Instant INSTANT_LOCAL_DATE = ZONE_DATE_TIME.toInstant();

    @Test
    void shouldCreateObjective(@Mock CreateObjectiveInput input, @Mock Employee employee) {
        when(objectiveMapper.mapObjectiveInputToEntity(any(CreateObjectiveInput.class), any(EmployeeEntity.class)))
                .thenReturn(createObjectiveEntity());
        when(employeeMapper.mapEmployeeToEmployeeEntity(any(Employee.class))).thenReturn(createEmployeeEntity());
        when(objectiveRepository.save(any(ObjectiveEntity.class))).thenReturn(createObjectiveEntity());
        when(objectiveMapper.mapObjectiveEntityToObjective(any(ObjectiveEntity.class))).thenReturn(createObjectivePojo());

        var result = objectiveService.createObjective(input, employee);

        assertNotNull(result);
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(ZONE_DATE_TIME, result.getDueToDate());
        assertEquals(COMMENT, result.getComments());
        assertEquals(TEST_UUID, result.getEmployee().getUuid());
        assertEquals(STATUS, result.getStatus());
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(ZONE_DATE_TIME, result.getEmployee().getBirthday());
    }

    @Test
    void shouldUpdateObjective(@Mock UpdateObjectiveInput input, @Mock Employee employee, @Mock Objective objectiveToBeUpdated) {
        when(objectiveMapper.mapInputToEntityWhenUpdate(any(UpdateObjectiveInput.class),
                any(ObjectiveEntity.class),
                any(EmployeeEntity.class)))
                .thenReturn(createObjectiveEntity());
        when(objectiveMapper.mapObjectiveToObjectiveEntity(any(Objective.class))).thenReturn(createObjectiveEntity());
        when(employeeMapper.mapEmployeeToEmployeeEntity(any(Employee.class))).thenReturn(createEmployeeEntity());
        when(objectiveMapper.mapObjectiveEntityToObjective(any(ObjectiveEntity.class))).thenReturn(createObjectivePojo());
        when(objectiveRepository.save(any(ObjectiveEntity.class))).thenReturn(createObjectiveEntity());

        var result = objectiveService.updateObjective(input, employee, objectiveToBeUpdated);

        assertNotNull(result);
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(ZONE_DATE_TIME, result.getDueToDate());
        assertEquals(COMMENT, result.getComments());
        assertEquals(TEST_UUID, result.getEmployee().getUuid());
        assertEquals(STATUS, result.getStatus());
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(ZONE_DATE_TIME, result.getEmployee().getBirthday());
    }

    @Test
    void shouldDeleteObjective(@Mock Objective objectiveToBeDeleted) {
        when(objectiveMapper.mapObjectiveToObjectiveEntity(any(Objective.class))).thenReturn(createObjectiveEntity());
        when(objectiveMapper.mapObjectiveEntityToObjective(any(ObjectiveEntity.class))).thenReturn(createObjectivePojo());

        var result = objectiveService.deleteObjective(objectiveToBeDeleted);

        assertNotNull(result);
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(ZONE_DATE_TIME, result.getDueToDate());
        assertEquals(COMMENT, result.getComments());
        assertEquals(TEST_UUID, result.getEmployee().getUuid());
        assertEquals(STATUS, result.getStatus());
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(ZONE_DATE_TIME, result.getEmployee().getBirthday());
    }

    @Test
    void shouldReturnObjectiveByUuid() {

        when(objectiveRepository.findByUuid(any(UUID.class))).thenReturn(Optional.ofNullable(createObjectiveEntity()));
        when(objectiveMapper.mapObjectiveEntityToObjective(any(ObjectiveEntity.class))).thenReturn(createObjectivePojo());

        var result = objectiveService.findObjectiveByUuid(TEST_UUID);

        assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getUuid());
    }

    @Test
    void shouldThrowDataNotFoundWhenUuidIsNull() {
        when(objectiveRepository.findByUuid(any(UUID.class)))
                .thenThrow(new DataNotFoundException("Objective not found for :" + TEST_UUID));
        Assertions.assertThrows(DataNotFoundException.class, () -> objectiveService.findObjectiveByUuid(TEST_UUID));
    }

    private ObjectiveEntity createObjectiveEntity() {
        return ObjectiveEntity.builder()
                .id(ID)
                .name(NAME)
                .comments(COMMENT)
                .description(DESCRIPTION)
                .dueToDate(INSTANT_LOCAL_DATE)
                .status(ObjectiveStatus.NEW)
                .employee(createEmployeeEntity())
                .build();
    }

    private EmployeeEntity createEmployeeEntity() {
        EmployeeEntity e = new EmployeeEntity();
        e.setId(ID);
        e.setUuid(TEST_UUID);
        e.setFirstName(NAME);
        e.setLastName(NAME);
        e.setBirthday(INSTANT_LOCAL_DATE);
        return e;
    }

    private CreateObjectiveInput createObjectiveInput() {
        return CreateObjectiveInput.builder()
                .name(NAME)
                .comments(COMMENT)
                .description(DESCRIPTION)
                .dueToDate(ZONE_DATE_TIME)
                .employee(TEST_UUID)
                .status(ObjectiveStatus.NEW)
                .build();
    }

    private UpdateObjectiveInput createUpdateObjectiveEntity() {
        return UpdateObjectiveInput.builder()
                .uuid(TEST_UUID)
                .name(NAME)
                .comments(COMMENT)
                .description(DESCRIPTION)
                .dueToDate(ZONE_DATE_TIME)
                .status(STATUS)
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

    private Objective createObjectivePojo() {
        return Objective.builder()
                .id(ID)
                .uuid(TEST_UUID)
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
                .id(ID)
                .uuid(TEST_UUID)
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
                .id(ID)
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }

    private Employee createReportingManager() {
        return new Employee();
    }

    private EmployeePosition createEmployeePosition() {
        return EmployeePosition.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }
}
