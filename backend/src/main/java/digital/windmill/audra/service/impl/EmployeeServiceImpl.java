package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.EmployeeSpecification;
import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.repository.EmployeeRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.mapper.EmployeePositionMapper;
import digital.windmill.audra.graphql.mapper.LocationMapper;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import digital.windmill.audra.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;
    private LocationMapper locationMapper;
    private EmployeePositionMapper employeePositionMapper;

    @Override
    public Employee createEmployee(CreateEmployeeInput input,
                                   EmployeePosition employeePosition,
                                   Location location) {
        EmployeeEntity reportingManager = employeeRepository.findByUuid(input.getReportingManager())
                .orElseThrow(
                        () -> new DataNotFoundException("Reporting manager not found for : " +
                                input.getReportingManager())
                );
        var toBeSavedEmployeeEntity = employeeMapper.mapEmployeeInputToEmployeeEntity(
                input,
                reportingManager,
                employeePositionMapper.mapEmployeePositionToEmployeePositionEntity(employeePosition),
                locationMapper.mapLocationToLocationEntity(location));
        return employeeMapper.mapEmployeeEntityToEmployee(employeeRepository.save(toBeSavedEmployeeEntity));
    }

    @Override
    public Employee findEmployeeByUuid(UUID uuid) {
        EmployeeEntity employeeEntity = employeeRepository.findByUuid(uuid).orElseThrow(
                () -> new DataNotFoundException("Employee not found for : " + uuid.toString())
        );
        return employeeMapper.mapEmployeeEntityToEmployee(employeeEntity);
    }

    @Override
    public Page<Employee> getEmployees(EmployeesInput input) {
        var spec = EmployeeSpecification.employees(input);
        Page<EmployeeEntity> allEmployees = employeeRepository.findAll(spec.getKey(), spec.getValue());
        return allEmployees.map(employeeMapper::mapEmployeeEntityToEmployee);
    }
}
