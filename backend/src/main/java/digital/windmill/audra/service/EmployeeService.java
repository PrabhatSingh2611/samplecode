package digital.windmill.audra.service;

import digital.windmill.audra.dao.EmployeeSpecification;
import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.repository.EmployeePositionRepository;
import digital.windmill.audra.dao.repository.EmployeeRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import digital.windmill.audra.graphql.type.input.NodeInput;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeePositionRepository employeePositionRepo;

    public EmployeeEntity findByUuid(UUID uuid) {
        return employeeRepository.findByUuid(uuid).orElseThrow(
                () -> new DataNotFoundException("Employee not found.")
        );
    }

    public EmployeeEntity findByLocation(NodeInput location) {
        return employeeRepository.findByUuid(location.getUuid()).orElseThrow(
                () -> new DataNotFoundException("Location not found.")
        );
    }

    public Page<EmployeeEntity> findAll(EmployeesInput input) {
        var spec = EmployeeSpecification.employees(input);
        return employeeRepository.findAll(spec.getKey(), spec.getValue());
    }

    public EmployeeEntity createEmployee(CreateEmployeeInput input){
        return employeeRepository.save(EmployeeEntity
                .builder()
                .uuid(UUID.randomUUID())
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .position(employeePositionRepo.findByUuid(input.getPosition()).orElse(null))
                .build());
    }
}
