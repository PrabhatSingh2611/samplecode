package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.graphql.facade.impl.EmployeeFacadeImpl;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import digital.windmill.audra.service.EmployeePositionService;
import digital.windmill.audra.service.EmployeeService;
import digital.windmill.audra.service.LocationService;
import org.junit.jupiter.api.Assertions;
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


    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final Long ID = 1L;
    private static final String NAME = "Name";
    private static final String ROLE = "6njELdS";
    private static final EmployeeRole ENUM_ROLE = EmployeeRole.ADMIN;
    private static final ZonedDateTime DATE_TIME = ZonedDateTime.now();
    private static final Instant DATE_TIME_INSTANT = DATE_TIME.toInstant();
    @Mock
    private EmployeePositionService employeePositionService;
    @Mock
    private LocationService locationService;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private EmployeeMapper employeeMapper;
    @InjectMocks
    EmployeeFacadeImpl facade;

    @Test
    void shouldReturnEmployeesById() {

        when(employeeService.findEmployeeByUuid(any(UUID.class)))
                .thenReturn(createEmployeeEntity());
        when(employeeMapper.mapEmployeeEntityToEmployee(any(EmployeeEntity.class))).thenReturn(createEmployee());

        var result = facade.findEmployeeByUuid(TEST_UUID);

        assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getUuid());
        Assertions.assertEquals(NAME, result.getFirstName());
        Assertions.assertEquals(NAME, result.getLastName());
        Assertions.assertEquals(NAME, result.getLocation().getName());
        Assertions.assertEquals(NAME, result.getPosition().getName());
    }

    @Test
    void shouldReturnAllEmployees(@Mock EmployeesInput employeesInput) {

        when(employeeService.getEmployees(any(EmployeesInput.class)))
                .thenReturn(createListOfEmployeeEntity());

        var result = facade.getEmployees(employeesInput);

        assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getContent().get(0).getUuid());
        Assertions.assertEquals(NAME, result.getContent().get(0).getFirstName());
        Assertions.assertEquals(NAME, result.getContent().get(0).getLastName());
        Assertions.assertEquals(NAME, result.getContent().get(0).getLocation().getName());
    }

    @Test
    void shouldCreateEmployee() {

        when(employeeService.findEmployeeByUuid(any(UUID.class))).thenReturn(createEmployeeEntity());
        when(employeePositionService.findEmployeePositionByUuid(any(UUID.class))).thenReturn(createEmployeePosition());
        when(locationService.findLocationByUuid(any(UUID.class))).thenReturn(createLocation());
        when(employeeService.createEmployee(
                        any(CreateEmployeeInput.class),
                        any(EmployeeEntity.class),
                        any(EmployeePosition.class),
                        any(Location.class)
                )).thenReturn(createEmployee());

        var result = facade.createEmployee(createCreateEmployeeInput());

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(NAME, result.getFirstName());
        assertEquals(NAME, result.getLastName());
        assertEquals(DATE_TIME, result.getBirthday());
    }

    private EmployeeEntity createEmployeeEntity() {
        return EmployeeEntity.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .firstName(NAME)
                .lastName(NAME)
                .role(ENUM_ROLE)
                .reportingManager(createReportingManagerEntity())
                .location(createLocationEntity())
                .birthday(DATE_TIME_INSTANT)
                .position(createEmployeePositionEntity())
                .build();
    }

    private EmployeePositionEntity createEmployeePositionEntity() {
        return EmployeePositionEntity
                .builder()
                .id(ID)
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }

    private LocationEntity createLocationEntity() {
        return LocationEntity.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }

    private EmployeeEntity createReportingManagerEntity() {
        return EmployeeEntity.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .firstName(NAME)
                .lastName(NAME)
                .role(ENUM_ROLE)
                .birthday(DATE_TIME_INSTANT)
                .build();
    }


    private Page<Employee> createListOfEmployeeEntity() {
        return new PageImpl<>(List.of(createEmployee()));
    }

    private EmployeePosition createEmployeePosition() {
        return EmployeePosition
                .builder()
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
                .position(createEmployeePosition())
                .location(createLocation())
                .build();
    }

    private Employee createReportingManager() {
        return Employee.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .lastName(NAME)
                .firstName(NAME)
                .position(createEmployeePosition())
                .location(createLocation())
                .role(ROLE)
                .reportingManager(null)
                .build();
    }


    private Location createLocation() {
        return Location.builder().uuid(TEST_UUID).name(NAME).build();
    }
}
