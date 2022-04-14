package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.EmployeeSpecification;
import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.repository.EmployeeRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import digital.windmill.audra.graphql.type.input.NodeInput;
import digital.windmill.audra.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;

    @Override public EmployeeEntity findByUuid(UUID uuid) {
        return employeeRepository.findByUuid(uuid).orElseThrow(
                () -> new DataNotFoundException("Employee not found for : " + uuid.toString())
        );
    }

    @Override public EmployeeEntity findByLocation(NodeInput location) {
        return employeeRepository.findByUuid(location.getUuid()).orElseThrow(
                () -> new DataNotFoundException("Location not found.")
        );
    }

    @Override public Page<EmployeeEntity> findAll(EmployeesInput input) {
        var spec = EmployeeSpecification.employees(input);
        return employeeRepository.findAll(spec.getKey(), spec.getValue());
    }

    @Override
    public Employee createEmployee(CreateEmployeeInput input,
                                   EmployeeEntity employeeEntity,
                                   EmployeePositionEntity employeePositionEntity,
                                   LocationEntity locationEntity) {
        var toBeSavedEmployeeEntity = employeeMapper.map(input,employeeEntity, employeePositionEntity, locationEntity);
        EmployeeEntity savedEmployee = employeeRepository.save(toBeSavedEmployeeEntity);
        return employeeMapper.map(savedEmployee);
    }
}
