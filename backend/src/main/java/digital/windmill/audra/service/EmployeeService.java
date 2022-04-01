package digital.windmill.audra.service;

import digital.windmill.audra.dao.EmployeeSpecification;
import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.repository.EmployeeRepository;
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

    public EmployeeEntity findByUuid(UUID uuid) {
        return employeeRepository.findByUuid(uuid).orElse(null);
    }

    public EmployeeEntity findByLocation(NodeInput location) {
        return employeeRepository.findByUuid(location.getUuid()).orElse(null);
    }

    public Page<EmployeeEntity> findAll(EmployeesInput input) {
        var spec = EmployeeSpecification.assets(input);
        return employeeRepository.findAll(spec.getKey(), spec.getValue());
    }
}
