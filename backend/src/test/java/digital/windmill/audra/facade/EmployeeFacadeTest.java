package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.repository.EmployeeRepository;
import digital.windmill.audra.graphql.facade.EmployeeFacade;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import digital.windmill.audra.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
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
public class EmployeeFacadeTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeFacade facade;

    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String FIRST_NAME = "Firstname";
    private static final String LAST_NAME = "Lastname";
    private static final String NAME = "Name";
    private static final Integer ITEM_PER_PAGE = 3;
    private static final Integer PAGE_NUMBER = 2;
    private final static Instant LOCAL_DATE = Instant.now();
    private static final String ROLE = "Admin";
    private static final String POSITION = "Position";
    private final static ZonedDateTime DATE_TIME = ZonedDateTime.now();

    @BeforeEach
    void config() {
        when(employeeMapper.map(any(EmployeeEntity.class)))
                .thenReturn(createEmployee());
    }

    @Test
    void shouldReturnEmployeesById() {

        when(employeeService.findByUuid(any(UUID.class)))
                .thenReturn(createEmployeeEntity());


        var result = facade.findAssetByUuid(TEST_UUID);

        assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getUuid());
        Assertions.assertEquals(FIRST_NAME, result.getFirstName());
        Assertions.assertEquals(LAST_NAME, result.getLastName());
        Assertions.assertEquals(NAME, result.getLocation().getName());
    }


    @Test
    void shouldReturnAllEmployees(@Mock EmployeesInput employeesInput) {

        when(employeeService.findAll(any(EmployeesInput.class)))
                .thenReturn(createListOfEmployeeEntity());

        var result = facade.getEmployees(employeesInput);

        assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getContent().get(0).getUuid());
        Assertions.assertEquals(FIRST_NAME, result.getContent().get(0).getFirstName());
        Assertions.assertEquals(LAST_NAME, result.getContent().get(0).getLastName());
        Assertions.assertEquals(NAME, result.getContent().get(0).getLocation().getName());
    }

    private Page<EmployeeEntity> createListOfEmployeeEntity() {
        return new PageImpl<>(List.of(createEmployeeEntity()));
    }


    @Test
    void shouldCreateEmployee() {

        when(employeeService.createEmployee(any(CreateEmployeeInput.class)))
                .thenReturn(createEmployeeEntity());
        var result = facade.createEmployee(createCreateEmployeeInput());

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(FIRST_NAME, result.getFirstName());
        assertEquals(LAST_NAME, result.getLastName());
        assertEquals(null, result.getPosition());
        assertEquals(DATE_TIME, result.getBirthday());
    }

    private CreateEmployeeInput createCreateEmployeeInput() {
        return CreateEmployeeInput.builder()
                .firstName(NAME)
                .lastName(NAME)
                .location(TEST_UUID)
                .position(TEST_UUID)
                .birthday(DATE_TIME)
                .build();
    }

    private Employee createEmployee() {
        return Employee.builder()
                .uuid(TEST_UUID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .role(ROLE)
                .birthday(DATE_TIME)
                .location(createLocation())
                .build();
    }

    private EmployeeEntity createEmployeeEntity() {
        EmployeeEntity e = new EmployeeEntity();
        e.setId(1L);
        e.setUuid(TEST_UUID);
        e.setFirstName(FIRST_NAME);
        e.setLastName(LAST_NAME);
        e.setBirthday(LOCAL_DATE);

        return e;
    }

    private Location createLocation() {
        return Location.builder().id(1L).uuid(TEST_UUID).name(NAME).build();
    }
}
