package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.EmployeeSpecification;
import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.repository.EmployeeRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.mapper.EmployeePositionMapper;
import digital.windmill.audra.graphql.mapper.LocationMapper;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import digital.windmill.audra.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;
    private LocationMapper locationMapper;
    private EmployeePositionMapper employeePositionMapper;

    @Override
    public Employee createEmployee(CreateEmployeeInput input, EmployeeEntity employeeReportingManager, EmployeePosition employeePosition, LocationEntity locationEntity) {

        var toBeSavedEmployeeEntity = employeeMapper.mapEmployeeInputToEmployeeEntity(
                input,
                employeeReportingManager,
                employeePositionMapper.mapEmployeePositionToEmployeePositionEntity(employeePosition),
                locationMapper.mapLocationToLocationEntity(locationEntity));

        return employeeMapper.mapEmployeeEntityToEmployee(employeeRepository.save(toBeSavedEmployeeEntity));
    }

    @Override
    public EmployeeEntity findEmployeeByUuid(UUID uuid) {
        if (Objects.nonNull(uuid)) {
            return employeeRepository.findByUuid(uuid).orElseThrow(
                    () -> new DataNotFoundException("Employee not found for : " + uuid)
            );
        }
        return null;
    }

    @Override
    public Page<Employee> getEmployees(EmployeesInput input) {
        var spec = EmployeeSpecification.employees(input);
        Page<EmployeeEntity> allEmployees = employeeRepository.findAll(spec.getKey(), spec.getValue());
        return allEmployees.map(employeeMapper::mapEmployeeEntityToEmployee);
    }
}
