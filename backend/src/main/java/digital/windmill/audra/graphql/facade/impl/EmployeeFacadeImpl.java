package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.EmployeeFacade;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import digital.windmill.audra.graphql.type.input.UpdateEmployeeInput;
import digital.windmill.audra.service.EmployeePositionService;
import digital.windmill.audra.service.EmployeeService;
import digital.windmill.audra.service.impl.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class EmployeeFacadeImpl implements EmployeeFacade {

    private EmployeeService employeeService;
    private EmployeePositionService employeePositionService;
    private LocationService locationService;
    private EmployeeMapper employeeMapper;

    @Transactional(readOnly = true)
    public Employee findEmployeeByUuid(UUID uuid) {
        return employeeMapper.mapEmployeeEntityToEmployee(employeeService.findEmployeeByUuid(uuid));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Employee> getEmployees(EmployeesInput input) {
        return employeeService.getEmployees(input)
                .map(employeeMapper::mapEmployeeEntityToEmployee);
    }

    @Override
    @Transactional
    public Employee createEmployee(CreateEmployeeInput input) {
        var employeeEntity = employeeMapper.mapEmployeeInputToEmployeeEntity(
                input,
                employeeService.findEmployeeByUuid(input.getReportingManager()),
                employeePositionService.findEmployeePositionByUuid(input.getPosition()),
                locationService.findLocationByUuid(input.getLocation()));
        var savedEmployeeEntity = employeeService.save(employeeEntity);
        return employeeMapper.mapEmployeeEntityToEmployee(savedEmployeeEntity);
    }

    @Override
    @Transactional
    public Employee updateEmployee(UpdateEmployeeInput input) {
        var employeeEntity = employeeMapper.mapUpdateEmployeeInputToEmployeeEntity(
                input,
                employeeService.findEmployeeByUuid(input.getId()),
                employeeService.findEmployeeByUuid(input.getReportingManager()),
                employeePositionService.findEmployeePositionByUuid(input.getPosition()),
                locationService.findLocationByUuid(input.getLocation()));
        var updatedEmployeeEntity = employeeService.save(employeeEntity);
        return employeeMapper.mapEmployeeEntityToEmployee(updatedEmployeeEntity);
    }
}