package digital.windmill.audra.facade;

import digital.windmill.audra.dao.EmployeeSpecification;
import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.repository.EmployeeRepository;
import digital.windmill.audra.graphql.facade.EmployeeFacade;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.input.EmployeeWhereInput;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import digital.windmill.audra.graphql.type.input.NodeInput;
import digital.windmill.audra.graphql.type.input.PageInput;
import digital.windmill.audra.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;

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
    private static final Integer ITEM_PER_PAGE = 3;
    private static final Integer PAGE_NUMBER = 2;
    private final static Instant LOCAL_DATE = Instant.now();
    private static final String ROLE = "Admin";
    private static final EmployeePosition POSITION = new EmployeePosition();
    private static final String LOCATION = "Location";
    private final static ZonedDateTime DATE_TIME = ZonedDateTime.now();

    @Test
    void shouldReturnEmployeesById() {

        when(employeeService.findByUuid(any(UUID.class)))
                .thenReturn(createEmployeeEntity());

        when(employeeMapper.map(any(EmployeeEntity.class)))
                .thenReturn(createEmployee());


        var result = facade.findAssetByUuid(TEST_UUID);

        assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getUuid());
        Assertions.assertEquals(FIRST_NAME, result.getFirstName());
        Assertions.assertEquals(LAST_NAME, result.getLastName());
        Assertions.assertEquals(LOCATION, result.getLocation());
    }

    private Employee createEmployee() {
        return Employee.builder()
                .uuid(TEST_UUID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .role(ROLE)
                .birthday(DATE_TIME)
                .position(POSITION)
                .location(LOCATION)
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
}
