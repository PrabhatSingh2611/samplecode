package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import digital.windmill.audra.dao.entity.LocationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import digital.windmill.audra.dao.entity.EmployeeEntity;
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

@ExtendWith(MockitoExtension.class)
class EmployeeFacadeTest {


    private static final UUID EMPLOYEE_UUID = UUID.randomUUID();
    private static final UUID REPORTING_MANAGER_UUID = UUID.randomUUID();
    private static final UUID POSITION_UUID = UUID.randomUUID();
    private static final UUID LOCATION_UUID = UUID.randomUUID();

    @Mock
    private EmployeePositionService employeePositionService;
    @Mock
    private LocationService locationService;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeFacadeImpl facade;

    @Test
    void shouldReturnEmployeesById(@Mock EmployeeEntity employeeEntity,
            @Mock Employee employee) {

        when(employeeService.findEmployeeByUuid(EMPLOYEE_UUID))
                .thenReturn(employeeEntity);
        when(employeeMapper.mapEmployeeEntityToEmployee(employeeEntity)).thenReturn(employee);

        var result = facade.findEmployeeByUuid(EMPLOYEE_UUID);

        assertNotNull(result);
        assertSame(employee, result);
    }

    @Test
    void shouldReturnAllEmployees(@Mock EmployeesInput employeesInput,
            @Mock Employee employee) {

        Page<Employee> employeesPage = createOneItemPage(employee);
        when(employeeService.getEmployees(any(EmployeesInput.class)))
                .thenReturn(employeesPage);

        var result = facade.getEmployees(employeesInput);

        assertNotNull(result);
        assertSame(employeesPage, result);
    }

    @Test
    void shouldCreateEmployee(
            @Mock CreateEmployeeInput input,
            @Mock Employee employee,
            @Mock EmployeePosition position,
            @Mock LocationEntity locationEntity,
            @Mock EmployeeEntity reportManagerEntity) {

        when(input.getReportingManager()).thenReturn(REPORTING_MANAGER_UUID);
        when(input.getLocation()).thenReturn(LOCATION_UUID);
        when(input.getPosition()).thenReturn(POSITION_UUID);
        when(employeeService.findEmployeeByUuid(REPORTING_MANAGER_UUID)).thenReturn(reportManagerEntity);
        when(employeePositionService.findEmployeePositionByUuid(POSITION_UUID)).thenReturn(position);
        when(locationService.findLocationByUuid(LOCATION_UUID)).thenReturn(locationEntity);
        when(employeeService.createEmployee(input,
                reportManagerEntity,
                position,
                locationEntity
            )).thenReturn(employee);

        var result = facade.createEmployee(input);

        assertNotNull(result);
        assertSame(employee, result);
    }


    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }
}
