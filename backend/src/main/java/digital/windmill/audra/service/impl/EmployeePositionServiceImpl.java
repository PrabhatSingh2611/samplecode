package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.repository.EmployeePositionRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.service.EmployeePositionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class EmployeePositionServiceImpl implements EmployeePositionService {
    private EmployeePositionRepository repo;

    @Override
    public EmployeePositionEntity createEmployeePosition(EmployeePositionEntity employeePosition) {
        return repo.save(employeePosition);
    }

    @Override
    public EmployeePositionEntity updateEmployeePosition(EmployeePosition employeePosition) {
        EmployeePositionEntity entity = repo.findByUuid(employeePosition.getUuid())
                .orElseThrow(() -> new DataNotFoundException("Employee Position not found"));
        entity.setName(employeePosition.getName());
        return repo.save(entity);
    }

    @Override
    public EmployeePositionEntity deleteEmployeePosition(EmployeePosition employeePosition) {
        EmployeePositionEntity entity = repo.findByUuid(employeePosition.getUuid())
                .orElseThrow(() -> new DataNotFoundException("Employee Position not found"));
        repo.delete(entity);
        return entity;
    }

    @Override
    public EmployeePositionEntity findEmployeePositionByUuid(UUID position) {
        return repo.findByUuid(position)
                .orElseThrow(() -> new DataNotFoundException("Employee Position Not found"));
    }
}
