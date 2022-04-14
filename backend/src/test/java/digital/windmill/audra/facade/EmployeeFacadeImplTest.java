package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.graphql.facade.impl.EmployeeFacadeImpl;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import digital.windmill.audra.service.EmployeePositionService;
import digital.windmill.audra.service.impl.EmployeePositionServiceImpl;
import digital.windmill.audra.service.impl.EmployeeServiceImpl;
import digital.windmill.audra.service.impl.LocationServiceImpl;
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
public class EmployeeFacadeImplTest {

    @Mock
    private EmployeePositionServiceImpl employeePositionService;
    @Mock
    private LocationServiceImpl locationService;
    @Mock
    private EmployeeServiceImpl employeeService;
    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeFacadeImpl facade;

    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final Long ID = 1L;
    private static final String NAME = "Name";
    private static final Integer ITEM_PER_PAGE = 3;
    private static final Integer PAGE_NUMBER = 2;
    private final static Instant LOCAL_DATE = Instant.now();
    private static final String ROLE = "6njELdS";
    private static final EmployeeRole ENUM_ROLE = EmployeeRole.ADMIN;
    private static final String POSITION = "Position";
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
        Assertions.assertEquals(NAME, result.getFirstName());
        Assertions.assertEquals(NAME, result.getLastName());
        Assertions.assertEquals(NAME, result.getLocation().getName());
    }


    @Test
    void shouldReturnAllEmployees(@Mock EmployeesInput employeesInput) {

        when(employeeService.findAll(any(EmployeesInput.class)))
                .thenReturn(createListOfEmployeeEntity());

        when(employeeMapper.map(any(EmployeeEntity.class)))
                .thenReturn(createEmployee());

        var result = facade.getEmployees(employeesInput);

        assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getContent().get(0).getUuid());
        Assertions.assertEquals(NAME, result.getContent().get(0).getFirstName());
        Assertions.assertEquals(NAME, result.getContent().get(0).getLastName());
        Assertions.assertEquals(NAME, result.getContent().get(0).getLocation().getName());
    }

    private Page<EmployeeEntity> createListOfEmployeeEntity() {
        return new PageImpl<>(List.of(createEmployeeEntity()));
    }


    @Test
    void shouldCreateEmployee() {

        when(employeePositionService.findEmployeePositionByUuid(any(UUID.class))).thenReturn(createEmployeePositionEntity());
        when(locationService.findByUuid(any(UUID.class))).thenReturn(createLocationEntity());
        when(employeeService.findByUuid(any(UUID.class))).thenReturn(createEmployeeEntity());
        when(employeeService.createEmployee(
                        any(CreateEmployeeInput.class),
                        any(EmployeeEntity.class),
                        any(EmployeePositionEntity.class),
                        any(LocationEntity.class)
                )
        ).thenReturn(createEmployee());

        var result = facade.createEmployee(createCreateEmployeeInput());

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(NAME, result.getFirstName());
        assertEquals(NAME, result.getLastName());
        assertEquals(DATE_TIME, result.getBirthday());
    }

    private LocationEntity createLocationEntity() {
        return LocationEntity.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }

    private EmployeePositionEntity createEmployeePositionEntity() {
        return EmployeePositionEntity.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }

    private CreateEmployeeInput createCreateEmployeeInput() {
        return CreateEmployeeInput.builder()
                .firstName(NAME)
                .lastName(NAME)
                .role(ENUM_ROLE)
                .birthday(DATE_TIME)
                .reportingManager(TEST_UUID)
                .position(TEST_UUID)
                .location(TEST_UUID)
                .build();
    }

    private Employee createEmployee() {
        return Employee.builder()
                .uuid(TEST_UUID)
                .firstName(NAME)
                .lastName(NAME)
                .role(ROLE)
                .birthday(DATE_TIME)
                .reportingManager(createReportingManager())
                .position(POSITION)
                .location(createLocation())
                .build();
    }

    private Employee createReportingManager() {
        return new Employee();
    }

    private EmployeeEntity createEmployeeEntity() {
        EmployeeEntity e = new EmployeeEntity();
        e.setId(ID);
        e.setUuid(TEST_UUID);
        e.setFirstName(NAME);
        e.setLastName(NAME);
        e.setBirthday(LOCAL_DATE);
        return e;
    }

    private Location createLocation() {
        return Location.builder().uuid(TEST_UUID).name(NAME).build();
    }
}
