package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.repository.EmployeePositionRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.EmployeePosition;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class EmployeePositionService {
    private EmployeePositionRepository repo;

    public EmployeePositionEntity findEmployeePositionByUuid(UUID uuid) {
        return repo.findByUuid(uuid).orElseThrow(
                () -> new DataNotFoundException("Employee position not found.")
        );
    }

    public EmployeePositionEntity createEmployeePosition(EmployeePositionEntity employeePosition) {
        return repo.save(employeePosition);
    }

    public EmployeePositionEntity updateEmployeePosition(EmployeePosition employeePosition) {
        EmployeePositionEntity entity = repo.findByUuid(employeePosition.getUuid()).orElseThrow(
                () -> new DataNotFoundException("Employee position not found.")
        );
        entity.setName(employeePosition.getName());
        return repo.save(entity);
    }

    public EmployeePositionEntity deleteEmployeePosition(EmployeePosition employeePosition) {
        EmployeePositionEntity entity = repo.findByUuid(employeePosition.getUuid())
                .orElseThrow(
                        () -> new DataNotFoundException("Employee position not found.")
                );
        repo.delete(entity);
        return entity;
    }
}
