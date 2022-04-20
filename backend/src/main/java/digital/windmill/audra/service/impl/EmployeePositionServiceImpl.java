package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.repository.EmployeePositionRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.EmployeePositionMapper;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.input.CreateEmployeePositionInput;
import digital.windmill.audra.graphql.type.input.UpdateEmployeePositionInput;
import digital.windmill.audra.service.EmployeePositionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class EmployeePositionServiceImpl implements EmployeePositionService {

    private EmployeePositionRepository employeePositionRepository;
    private EmployeePositionMapper employeePositionMapper;

    @Override
    public EmployeePosition createEmployeePosition(CreateEmployeePositionInput input) {
        var toBeSavedEmployeePositionEntity = employeePositionMapper
                .mapCreateEmployeePositionInputToEmployeePositionEntity(input);
        EmployeePositionEntity savedEmployee = employeePositionRepository.save(toBeSavedEmployeePositionEntity);
        return employeePositionMapper.mapEmployeePositionEntityToEmployeePosition(savedEmployee);
    }

    @Override
    public EmployeePosition updateEmployeePosition(
            UpdateEmployeePositionInput input,
            EmployeePosition employeePosition) {
        var employeePositionEntity = employeePositionMapper
                .mapUpdateToEmployeePositionEntity(input, employeePosition);
        var savedEmployeePositionEntity = employeePositionRepository.save(employeePositionEntity);

        return employeePositionMapper.mapEmployeePositionEntityToEmployeePosition(savedEmployeePositionEntity);
    }

    @Override
    public EmployeePosition deleteEmployeePosition(EmployeePosition employeePosition) {
        var employeePositionEntity = employeePositionMapper
                .mapEmployeePositionToEmployeePositionEntity(employeePosition);
        employeePositionRepository.delete(employeePositionEntity);
        return employeePositionMapper.mapEmployeePositionEntityToEmployeePosition(employeePositionEntity);
    }

    @Override
    public EmployeePosition findEmployeePositionByUuid(UUID uuid) {
        return employeePositionMapper
                .mapEmployeePositionEntityToEmployeePosition(
                        employeePositionRepository
                                .findByUuid(uuid)
                                .orElseThrow(
                                        () -> new DataNotFoundException("Employee Position not found for : " + uuid.toString())
                                )
                );
    }
}
