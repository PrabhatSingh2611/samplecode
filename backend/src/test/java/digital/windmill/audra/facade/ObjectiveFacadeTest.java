package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.enums.ObjectiveStatus;
import digital.windmill.audra.graphql.facade.impl.ObjectiveFacadeImpl;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.DeleteObjectiveInput;
import digital.windmill.audra.graphql.type.input.EmployeeObjectiveInput;
import digital.windmill.audra.graphql.type.input.UpdateObjectiveInput;
import digital.windmill.audra.service.EmployeeService;
import digital.windmill.audra.service.ObjectiveService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObjectiveFacadeTest {

    @Mock
    private ObjectiveService objectiveService;
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    ObjectiveFacadeImpl facade;

    private static final UUID TEST_UUID = UUID.fromString("7ed1f598-b03a-42be-91e9-f9503dde4acd");
    private static final String DESCRIPTION = "Description";
    private static final String NAME = "Name";
    private static final String ROLE = "Employee";
    private static final String COMMENT = "Comment";
    private static final ObjectiveStatus STATUS = ObjectiveStatus.NEW;
    private final static ZonedDateTime DATE_TIME = ZonedDateTime.now();
    private static final Long ID = 813L;


    @Test
    void shouldCreateObjective(@Mock CreateObjectiveInput input) {
        when(input.getEmployee()).thenReturn(createEmployeeObjective());
        when(employeeService.findEmployeeByUuid(any(UUID.class)))
                .thenReturn(createEmployee());
        when(objectiveService.createObjective(any(CreateObjectiveInput.class), any(Employee.class)))
                .thenReturn(createObjective());

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
        when(input.getUuid()).thenReturn(TEST_UUID);
        when(employeeService.findEmployeeByUuid(any(UUID.class)))
                .thenReturn(createEmployee());
        when(objectiveService.findObjectiveByUuid(any(UUID.class)))
                .thenReturn(createObjective());
        when(objectiveService.updateObjective(any(UpdateObjectiveInput.class),
                any(Employee.class), any(Objective.class)))
                .thenReturn(createObjective());

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
                .thenReturn(createObjective());
        when(objectiveService.deleteObjective(any(Objective.class)))
                .thenReturn(createObjective());

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

    private EmployeeObjectiveInput createEmployeeObjective() {
        return EmployeeObjectiveInput.builder()
                .uuid(TEST_UUID)
                .build();
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
}