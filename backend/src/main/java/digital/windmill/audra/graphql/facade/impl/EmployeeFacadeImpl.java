package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.EmployeeFacade;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import digital.windmill.audra.service.EmployeePositionService;
import digital.windmill.audra.service.EmployeeService;
import digital.windmill.audra.service.LocationService;
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

    @Transactional(readOnly = true)
    public Employee findEmployeeByUuid(UUID uuid) {
        return employeeService.findByUuidMapped(uuid);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Employee> getEmployees(EmployeesInput input) {
        return employeeService.getEmployees(input);
    }

    @Override
    public Employee createEmployee(CreateEmployeeInput input) {
        var employeePositionEntity = employeePositionService.findByUuid(input.getPosition());
        var location = locationService.findLocationByUuid(input.getLocation());
        var employeeEntity = employeeService.findByUuid(input.getReportingManager());

        return employeeService.createEmployee(input, employeeEntity, employeePositionEntity, location);
    }
}