package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.facade.impl.EmployeeFacadeImpl;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import digital.windmill.audra.graphql.type.input.UpdateEmployeeInput;
import digital.windmill.audra.service.EmployeePositionService;
import digital.windmill.audra.service.EmployeeService;
import digital.windmill.audra.service.LocationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeFacadeTest {

    private static final UUID EMPLOYEE_UUID = UUID.randomUUID();

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
    void shouldReturnEmployeesById2(@Mock EmployeeEntity employeeEntity, @Mock Employee employee) {
        when(employeeService.findEmployeeByUuid(EMPLOYEE_UUID)).thenReturn(employeeEntity);
        when(employeeMapper.mapEmployeeEntityToEmployee(employeeEntity)).thenReturn(employee);

        var result = facade.findEmployeeByUuid(EMPLOYEE_UUID);

        assertEquals(employee, result);
    }

    @Test
    void shouldReturnAllEmployees(@Mock EmployeeEntity employeeEntity,
                                  @Mock Employee employee,
                                  @Mock EmployeesInput employeesInput
    ) {

        Page<EmployeeEntity> employeesPage = createOneItemPage(employeeEntity);
        when(employeeService.getEmployees(any(EmployeesInput.class)))
                .thenReturn(employeesPage);
        when(employeeMapper.mapEmployeeEntityToEmployee(any(EmployeeEntity.class)))
                .thenReturn(employee);
        var result = facade.getEmployees(employeesInput);

        assertNotNull(result);
        assertEquals(employee, result.getContent().get(0));
    }

    @Test
    void shouldCreateEmployee(@Mock CreateEmployeeInput input,
                              @Mock EmployeeEntity employeeEntity,
                              @Mock EmployeePositionEntity employeePositionEntity,
                              @Mock LocationEntity locationEntity,
                              @Mock Employee employee) {


        when(employeeMapper.mapEmployeeInputToEmployeeEntity(eq(input), any(EmployeeEntity.class), any(EmployeePositionEntity.class), any(LocationEntity.class)))
                .thenReturn(employeeEntity);
        when(employeeService.save(employeeEntity)).thenReturn(employeeEntity);
        when(employeeMapper.mapEmployeeEntityToEmployee(employeeEntity)).thenReturn(employee);

        when(employeeService.findEmployeeByUuid(input.getReportingManager())).thenReturn(employeeEntity);
        when(employeePositionService.findEmployeePositionByUuid(input.getPosition())).thenReturn(employeePositionEntity);
        when(locationService.findLocationByUuid(input.getLocation())).thenReturn(locationEntity);

        var actualResult = facade.createEmployee(input);

        assertEquals(employee, actualResult);
    }

    @Test
    void shouldUpdateEmployee(@Mock UpdateEmployeeInput input,
                              @Mock EmployeeEntity employeeEntity,
                              @Mock EmployeePositionEntity employeePositionEntity,
                              @Mock LocationEntity locationEntity,
                              @Mock Employee employee) {


        when(employeeMapper.mapUpdateEmployeeInputToEmployeeEntity(eq(input), any(EmployeeEntity.class), any(EmployeeEntity.class), any(EmployeePositionEntity.class), any(LocationEntity.class)))
                .thenReturn(employeeEntity);
        when(employeeService.save(employeeEntity)).thenReturn(employeeEntity);
        when(employeeMapper.mapEmployeeEntityToEmployee(employeeEntity)).thenReturn(employee);

        when(employeeService.findEmployeeByUuid(input.getId())).thenReturn(employeeEntity);
        when(employeeService.findEmployeeByUuid(input.getReportingManager())).thenReturn(employeeEntity);
        when(employeePositionService.findEmployeePositionByUuid(input.getPosition())).thenReturn(employeePositionEntity);
        when(locationService.findLocationByUuid(input.getLocation())).thenReturn(locationEntity);

        var actualResult = facade.updateEmployee(input);

        assertEquals(employee, actualResult);
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }
}
