package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.repository.EmployeePositionRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.service.EmployeePositionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class EmployeePositionServiceImpl implements EmployeePositionService {

    private EmployeePositionRepository employeePositionRepository;

    @Override
    public EmployeePositionEntity save(EmployeePositionEntity employeePositionEntity) {
        return employeePositionRepository.save(employeePositionEntity);
    }

    @Override
    public EmployeePositionEntity deleteEmployeePosition(EmployeePositionEntity employeePositionEntity) {
        employeePositionRepository.delete(employeePositionEntity);
        return employeePositionEntity;
    }

    @Override
    public EmployeePositionEntity findEmployeePositionByUuid(UUID uuid) {
        return employeePositionRepository.findByUuid(uuid)
                .orElseThrow(() -> new DataNotFoundException("Employee Position not found for : " + uuid.toString()));
    }
}
