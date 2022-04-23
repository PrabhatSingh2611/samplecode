package digital.windmill.audra.resolver;

import digital.windmill.audra.dao.entity.enums.ObjectiveStatus;
import digital.windmill.audra.graphql.facade.ObjectiveFacade;
import digital.windmill.audra.graphql.resolver.objective.ObjectiveMutationResolver;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.Locations;
import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.UpdateObjectiveInput;
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
class ObjectiveMutationResolverTest {

    private static final UUID TEST_UUID = UUID.fromString("7ed1f598-b03a-42be-91e9-f9503dde4acd");
    private static final String DESCRIPTION = "Description";
    private static final String NAME = "Name";
    private static final String ROLE = "Employee";
    private static final String COMMENT = "Comment";
    private static final ObjectiveStatus STATUS = ObjectiveStatus.NEW;
    private final static ZonedDateTime DATE_TIME = ZonedDateTime.now();
    private static final Long ID = 571L;

    @Mock
    private ObjectiveFacade facade;

    @InjectMocks
    ObjectiveMutationResolver resolver;

    @Test
    void shouldCreateObjective(@Mock CreateObjectiveInput input) {
        when(facade.createObjective(any(CreateObjectiveInput.class))).thenReturn(createObjective());

        var result = resolver.createObjective(input);

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getItem().getUuid());
        assertEquals(DESCRIPTION, result.getItem().getDescription());
        assertEquals(NAME, result.getItem().getName());
        assertEquals(STATUS, result.getItem().getStatus());
        assertEquals(COMMENT, result.getItem().getComments());
        assertEquals(DATE_TIME, result.getItem().getDueToDate());
        assertEquals(TEST_UUID, result.getItem().getEmployee().getUuid());
        assertEquals(TEST_UUID, result.getItem().getEmployee().getPosition().getUuid());
        assertEquals(ID, result.getItem().getId());
        assertEquals(ROLE, result.getItem().getEmployee().getRole());
        assertEquals(TEST_UUID, result.getItem().getEmployee().getLocation().getUuid());
        assertEquals(NAME, result.getItem().getEmployee().getLocation().getName());
    }

    @Test
    void shouldUpdateObjective(@Mock UpdateObjectiveInput input) {
        when(facade.updateObjective(any(UpdateObjectiveInput.class)))
                .thenReturn(createObjective());

        var result = resolver.updateObjective(input);

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getItem().getUuid());
        assertEquals(TEST_UUID, result.getItem().getEmployee().getUuid());
        assertEquals(TEST_UUID, result.getItem().getEmployee().getLocation().getUuid());
        assertEquals(TEST_UUID, result.getItem().getEmployee().getPosition().getUuid());
        assertEquals(ID, result.getItem().getId());
        assertEquals(NAME, result.getItem().getName());
        assertEquals(STATUS, result.getItem().getStatus());
        assertEquals(COMMENT, result.getItem().getComments());
        assertEquals(DATE_TIME, result.getItem().getDueToDate());
        assertEquals(ROLE, result.getItem().getEmployee().getRole());
        assertEquals(DESCRIPTION, result.getItem().getDescription());
        assertEquals(NAME, result.getItem().getEmployee().getLocation().getName());
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