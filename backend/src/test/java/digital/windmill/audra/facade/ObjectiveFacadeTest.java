package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.ObjectiveEntity;
import digital.windmill.audra.dao.entity.enums.ObjectiveStatus;
import digital.windmill.audra.graphql.facade.impl.ObjectiveFacadeImpl;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.mapper.ObjectiveMapper;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.DeleteObjectiveInput;
import digital.windmill.audra.graphql.type.input.ObjectivesInput;
import digital.windmill.audra.graphql.type.input.UpdateObjectiveInput;
import digital.windmill.audra.service.EmployeeService;
import digital.windmill.audra.service.ObjectiveService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObjectiveFacadeTest {
    private static final UUID TEST_UUID = UUID.randomUUID();
    private static final String DESCRIPTION = "Description";
    private static final String NAME = "Name";
    private static final String ROLE = "Employee";
    private static final String COMMENT = "Comment";
    private static final ObjectiveStatus STATUS = ObjectiveStatus.NEW;
    private static final ZonedDateTime DATE_TIME = ZonedDateTime.now();
    private static final Instant INSTANT_LOCAL_DATE = DATE_TIME.toInstant();
    private static final Long ID = 813L;
    @Mock
    private ObjectiveService objectiveService;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private ObjectiveMapper objectiveMapper;
    @InjectMocks
    ObjectiveFacadeImpl facade;
    @Test
    void shouldCreateObjective(@Mock CreateObjectiveInput input) {
        when(input.getEmployee()).thenReturn(TEST_UUID);
        when(employeeService.findEmployeeByUuid(any(UUID.class))).thenReturn(createEmployeeEntity());
        when(objectiveMapper.mapObjectiveInputToEntity(any(CreateObjectiveInput.class), any(EmployeeEntity.class)))
                .thenReturn(createObjectiveEntity());
        when(objectiveMapper.mapObjectiveEntityToObjective(any(ObjectiveEntity.class))).thenReturn(createObjective());
        when(objectiveService.createObjective(any(ObjectiveEntity.class))).thenReturn(createObjectiveEntity());

        var result = facade.createObjective(input);

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(NAME, result.getName());
        assertEquals(STATUS, result.getStatus());
        assertEquals(COMMENT, result.getComments());
        assertEquals(DATE_TIME, result.getDueToDate());
        assertEquals(TEST_UUID, result.getEmployee().getUuid());
        assertEquals(TEST_UUID, result.getEmployee().getPosition().getUuid());
        assertEquals(ID, result.getId());
        assertEquals(ROLE, result.getEmployee().getRole());
        assertEquals(TEST_UUID, result.getEmployee().getLocation().getUuid());
        assertEquals(NAME, result.getEmployee().getLocation().getName());
    }

    @Test
    void shouldUpdateObjective(@Mock UpdateObjectiveInput input) {
        when(input.getEmployee()).thenReturn(TEST_UUID);
        when(input.getUuid()).thenReturn(TEST_UUID);
        when(employeeService.findEmployeeByUuid(any(UUID.class))).thenReturn(createEmployeeEntity());
        when(objectiveService.findObjectiveByUuid(any(UUID.class))).thenReturn(createObjectiveEntity());
        when(objectiveMapper.mapInputToEntityWhenUpdate(any(UpdateObjectiveInput.class), any(ObjectiveEntity.class),
                any(EmployeeEntity.class))).thenReturn(createObjectiveEntity());
        when(objectiveMapper.mapObjectiveEntityToObjective(any(ObjectiveEntity.class))).thenReturn(createObjective());
        when(objectiveService.updateObjective(any(ObjectiveEntity.class))).thenReturn(createObjectiveEntity());

        var result = facade.updateObjective(input);

        assertNotNull(result);
        assertEquals(ID, result.getId());
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(NAME, result.getName());
        assertEquals(STATUS, result.getStatus());
        assertEquals(COMMENT, result.getComments());
        assertEquals(DATE_TIME, result.getDueToDate());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(ROLE, result.getEmployee().getRole());
        assertEquals(TEST_UUID, result.getEmployee().getUuid());
        assertEquals(NAME, result.getEmployee().getLocation().getName());
        assertEquals(TEST_UUID, result.getEmployee().getPosition().getUuid());
        assertEquals(TEST_UUID, result.getEmployee().getLocation().getUuid());
    }

    @Test
    void shouldDeleteObjective(@Mock DeleteObjectiveInput input) {
        when(input.getUuid()).thenReturn(TEST_UUID);
        when(objectiveService.findObjectiveByUuid(any(UUID.class)))
                .thenReturn(createObjectiveEntity());
        when(objectiveMapper.mapObjectiveEntityToObjective(any(ObjectiveEntity.class))).thenReturn(createObjective());
        when(objectiveService.deleteObjective(any(ObjectiveEntity.class)))
                .thenReturn(createObjectiveEntity());


        var result = facade.deleteObjective(input);

        assertNotNull(result);
        assertEquals(ID, result.getId());
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(NAME, result.getName());
        assertEquals(STATUS, result.getStatus());
        assertEquals(COMMENT, result.getComments());
        assertEquals(DATE_TIME, result.getDueToDate());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(ROLE, result.getEmployee().getRole());
        assertEquals(TEST_UUID, result.getEmployee().getUuid());
        assertEquals(NAME, result.getEmployee().getLocation().getName());
        assertEquals(TEST_UUID, result.getEmployee().getPosition().getUuid());
        assertEquals(TEST_UUID, result.getEmployee().getLocation().getUuid());
    }

    @Test
    void shouldFindObjectiveByUuid() {
        when(objectiveService.findObjectiveByUuid(any(UUID.class))).thenReturn(createObjectiveEntity());
        when(objectiveMapper.mapObjectiveEntityToObjective(any(ObjectiveEntity.class))).thenReturn(createObjective());

        var result = facade.findObjectiveByUuid(TEST_UUID);
        assertNotNull(result);
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(NAME, result.getName());
        assertEquals(STATUS, result.getStatus());
        assertEquals(COMMENT, result.getComments());
        assertEquals(DATE_TIME, result.getDueToDate());
        assertEquals(TEST_UUID, result.getEmployee().getUuid());
        assertEquals(TEST_UUID, result.getEmployee().getPosition().getUuid());
        assertEquals(ID, result.getId());
        assertEquals(ROLE, result.getEmployee().getRole());
        assertEquals(TEST_UUID, result.getEmployee().getLocation().getUuid());
        assertEquals(NAME, result.getEmployee().getLocation().getName());

    }

    @Test
    void shouldGetAllObjectives(@Mock ObjectivesInput input) {
        List<ObjectiveEntity> objectives = List.of(createObjectiveEntity());
        var paged = new PageImpl<>(objectives);
        when(objectiveService.findAllObjectives(any(ObjectivesInput.class))).thenReturn(paged);
        when(objectiveMapper.mapObjectiveEntityToObjective(any(ObjectiveEntity.class))).thenReturn(createObjective());

        var result = facade.getObjectives(input);
        assertNotNull(result);
        assertEquals(ID, result.getContent().get(0).getId());
        assertEquals(DESCRIPTION, result.getContent().get(0).getDescription());
        assertEquals(COMMENT, result.getContent().get(0).getComments());
        assertEquals(TEST_UUID, result.getContent().get(0).getUuid());
        assertEquals(TEST_UUID, result.getContent().get(0).getEmployee().getUuid());
        assertEquals(TEST_UUID, result.getContent().get(0).getEmployee().getLocation().getUuid());
        assertEquals(TEST_UUID, result.getContent().get(0).getEmployee().getPosition().getUuid());
    }


    private Objective createObjective() {
        return Objective.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .name(NAME)
                .description(DESCRIPTION)
                .comments(COMMENT)
                .dueToDate(DATE_TIME)
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
                .birthday(DATE_TIME)
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

    private EmployeeEntity createEmployeeEntity() {
        EmployeeEntity e = new EmployeeEntity();
        e.setId(ID);
        e.setUuid(TEST_UUID);
        e.setFirstName(NAME);
        e.setLastName(NAME);
        e.setBirthday(INSTANT_LOCAL_DATE);
        return e;
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
}