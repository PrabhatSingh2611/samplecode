package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.ObjectiveFacade;
import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.UpdateObjectiveInput;
import digital.windmill.audra.service.EmployeeService;
import digital.windmill.audra.service.ObjectiveService;
import digital.windmill.audra.service.impl.ObjectiveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ObjectiveFacadeImpl implements ObjectiveFacade {
    private ObjectiveService objectiveService;
    private EmployeeService employeeService;

    @Override
    public Objective createObjective(CreateObjectiveInput input){
        //return objectiveMapper.map(objectiveService.createObjective(input));
        var employeeEntity = employeeService.findByUuid(input.getEmployee().getUuid());
        return objectiveService.createObjective(input,employeeEntity);
    }

    public Objective updateObjective(UpdateObjectiveInput input){
        var employeeEntity = employeeService.findByUuid(input.getUuid());
        return objectiveService.updateObjective(input,employeeEntity);
    }
}
