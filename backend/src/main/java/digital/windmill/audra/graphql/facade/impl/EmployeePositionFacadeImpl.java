package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.EmployeePositionFacade;
import digital.windmill.audra.graphql.mapper.EmployeePositionMapper;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.input.CreateEmployeePositionInput;
import digital.windmill.audra.graphql.type.input.DeleteEmployeePositionInput;
import digital.windmill.audra.graphql.type.input.UpdateEmployeePositionInput;
import digital.windmill.audra.service.EmployeePositionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeePositionFacadeImpl implements EmployeePositionFacade {

    private EmployeePositionService employeePositionService;
    private EmployeePositionMapper employeePositionMapper;

    @Override
    public EmployeePosition createEmployeePosition(CreateEmployeePositionInput input) {
        var employeePositionEntity = employeePositionMapper.mapCreateEmployeePositionInputToEmployeePositionEntity(input);
        var savedEmployeePositionEntity = employeePositionService.save(employeePositionEntity);
        return employeePositionMapper.mapEmployeePositionEntityToEmployeePosition(savedEmployeePositionEntity);
    }

    @Override
    public EmployeePosition updateEmployeePosition(UpdateEmployeePositionInput input) {
        var employeePositionEntity = employeePositionService.findEmployeePositionByUuid(input.getId());
        employeePositionMapper.mapUpdateToEmployeePositionEntity(employeePositionEntity, input);
        var updatedEmployeePositionEntity = employeePositionService.save(employeePositionEntity);
        return employeePositionMapper.mapEmployeePositionEntityToEmployeePosition(updatedEmployeePositionEntity);
    }

    @Override
    public EmployeePosition deleteEmployeePosition(DeleteEmployeePositionInput input) {
        var employeePositionEntity = employeePositionService.findEmployeePositionByUuid(input.getId());
        var deletedEmployeePositionEntity = employeePositionService.deleteEmployeePosition(employeePositionEntity);
        return employeePositionMapper.mapEmployeePositionEntityToEmployeePosition(deletedEmployeePositionEntity);
    }

}
