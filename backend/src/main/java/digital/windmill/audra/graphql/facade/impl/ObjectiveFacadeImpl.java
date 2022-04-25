package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.ObjectiveFacade;
import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.DeleteObjectiveInput;
import digital.windmill.audra.graphql.type.input.UpdateObjectiveInput;
import digital.windmill.audra.service.EmployeeService;
import digital.windmill.audra.service.ObjectiveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ObjectiveFacadeImpl implements ObjectiveFacade {
    private ObjectiveService objectiveService;
    private EmployeeService employeeService;

    @Override
    public Objective createObjective(CreateObjectiveInput input) {
        var employee = employeeService.findEmployeeByUuid(input.getEmployee().getUuid());
        return objectiveService.createObjective(input, employee);
    }

    public Objective updateObjective(UpdateObjectiveInput input) {
        var employee = employeeService.findEmployeeByUuid(input.getUuid());
        var objectiveToBeUpdated = objectiveService.findObjectiveByUuid(input.getUuid());
        return objectiveService.updateObjective(input, employee, objectiveToBeUpdated);
    }

    @Override
    public Objective deleteObjective(DeleteObjectiveInput input) {
        var objectiveToBeDeleted = objectiveService.findObjectiveByUuid(input.getUuid());
        return objectiveService.deleteObjective(objectiveToBeDeleted);
    }
}
