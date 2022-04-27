package digital.windmill.audra.resolver;

import digital.windmill.audra.dao.entity.enums.ObjectiveStatus;
import digital.windmill.audra.graphql.facade.ObjectiveFacade;
import digital.windmill.audra.graphql.resolver.objective.ObjectiveResolver;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.ObjectiveInput;
import digital.windmill.audra.graphql.type.input.ObjectivesInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObjectiveResolverTest {

    private static final UUID TEST_UUID = UUID.randomUUID();
    private static final String DESCRIPTION = "8vw";
    private static final String NAME = "jxlEjlu";
    private static final String ROLE = "75Z90X";
    private static final String COMMENT = "J4Vp8";
    private static final ObjectiveStatus STATUS = ObjectiveStatus.NEW;
    private final static ZonedDateTime DATE_TIME = ZonedDateTime.now();
    private static final Long ID = 663L;

    @Mock
    private ObjectiveFacade objectiveFacade;

    @InjectMocks
    ObjectiveResolver objectiveResolver;

    @Test
    void shouldGetOjective(@Mock ObjectiveInput objectiveInput) {
        when(objectiveInput.getUuid()).thenReturn(TEST_UUID);
        when(objectiveFacade.findObjectiveByUuid(any(UUID.class))).thenReturn(createObjective());

        var result = objectiveResolver.objective(objectiveInput);
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
    void shouldGetAllObjectives(@Mock ObjectivesInput input) {
        List<Objective> objectives = List.of(createObjective());
        Page<Objective> pagedResponse = new PageImpl<>(objectives);
        when(objectiveFacade.getObjectives(any(ObjectivesInput.class))).thenReturn(pagedResponse);

        var result = objectiveResolver.objectives(input);
        assertNotNull(result);
        assertEquals(objectives, result.getItems());
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