package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.enums.ObjectiveStatus;
import digital.windmill.audra.graphql.facade.impl.ObjectiveFacadeImpl;
import digital.windmill.audra.graphql.mapper.ObjectiveMapper;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.EmployeeObjectiveInput;
import digital.windmill.audra.service.EmployeeService;
import digital.windmill.audra.service.impl.ObjectiveService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ObjectiveFacadeTest {


    @Mock
    ObjectiveService objectiveService;

    @Mock
    EmployeeService employeeService;

    @Mock
    ObjectiveMapper mapper;

    @InjectMocks
    ObjectiveFacadeImpl objectiveFacade;

    private static final Long ID= 1l;
    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String FIRST_NAME = "Firstname";
    private static final String LAST_NAME = "Lastname";
    private static final String NAME = "graphql";
    private static final String COMMENTS = "schema,etc";
    private static final String DESCRIPTION = "learning graphql";
    private static final ZonedDateTime DUE_TO_DATE = ZonedDateTime.parse("2022-07-28T12:03:00.480425Z");
    final static Instant LOCAL_DATE = Instant.now();
    private static final String POSITION = "Position";
    private final static ZonedDateTime DATE_TIME = ZonedDateTime.now();





   /* @Test
    void shouldCreateObjective( ){

        *//*var employeeEntity = employeeService.findByUuid(input.getEmployee().getUuid());
        return objectiveService.createObjective(input,employeeEntity);*//*

        when(objectiveService.createObjective(any(CreateObjectiveInput.class),any(EmployeeEntity.class)))
                .thenReturn(createObjective());
        when(employeeService.findEmployeeByUuid(any(UUID.class))).thenReturn(createEmployeeEntity());
        var result = objectiveFacade.createObjective(createObjectiveInput());
        assertNotNull(result);
        assertEquals(NAME, result.getName());
        assertEquals(COMMENTS, result.getComments());
        assertEquals(DUE_TO_DATE, result.getDueToDate());
    }*/

    private CreateObjectiveInput createObjectiveInput() {
       return CreateObjectiveInput.builder()
                .name(NAME)
                .comments(COMMENTS)
                .description(DESCRIPTION)
                .dueToDate(DUE_TO_DATE)
                .employee(EmployeeObjectiveInput.builder().uuid(TEST_UUID).build())
                .status(ObjectiveStatus.NEW)
                .build();
    }


    private EmployeeEntity createEmployeeEntity() {
        EmployeeEntity e = new EmployeeEntity();
        e.setId(ID);
        e.setUuid(TEST_UUID);
        e.setFirstName(FIRST_NAME);
        e.setLastName(LAST_NAME);
        e.setBirthday(LOCAL_DATE);

        return e;
    }

    private Objective createObjective(){
       return Objective.builder()
                .uuid(UUID.randomUUID())
                .name(NAME)
                .comments(COMMENTS)
                .description(DESCRIPTION)
                .dueToDate(DUE_TO_DATE)
                .employee(createEmployee())
                .status(ObjectiveStatus.NEW)
                .build();
    }

    private Employee createEmployee() {
       return Employee.builder()
                .uuid(TEST_UUID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
            //    .position(POSITION)
                .birthday(DATE_TIME)
                .build();
    }
}
