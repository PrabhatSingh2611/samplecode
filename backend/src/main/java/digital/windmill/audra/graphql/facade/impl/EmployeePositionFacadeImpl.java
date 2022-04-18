package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.EmployeePositionFacade;
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

    @Override
    public EmployeePosition createEmployeePosition(CreateEmployeePositionInput input) {
        return employeePositionService.createEmployeePosition(input);
    }

    @Override
    public EmployeePosition updateEmployeePosition(UpdateEmployeePositionInput input) {
        var employeePosition = employeePositionService.findByUuid(input.getUuid());
        return employeePositionService.updateEmployeePosition(input, employeePosition);
    }

    @Override
    public EmployeePosition deleteEmployeePosition(DeleteEmployeePositionInput input) {
        var employeePosition = employeePositionService.findByUuid(input.getUuid());
        return employeePositionService.deleteEmployeePosition(employeePosition);

    }


}
