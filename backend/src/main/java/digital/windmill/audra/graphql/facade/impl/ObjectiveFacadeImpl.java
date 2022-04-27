package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.ObjectiveFacade;
import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.Vacancy;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.DeleteObjectiveInput;
import digital.windmill.audra.graphql.type.input.ObjectivesInput;
import digital.windmill.audra.graphql.type.input.UpdateObjectiveInput;
import digital.windmill.audra.graphql.type.input.VacanciesInput;
import digital.windmill.audra.service.EmployeeService;
import digital.windmill.audra.service.ObjectiveService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ObjectiveFacadeImpl implements ObjectiveFacade {
    private ObjectiveService objectiveService;
    private EmployeeService employeeService;

    @Override
    public Objective createObjective(CreateObjectiveInput input) {
        var employee = employeeService.findEmployeeByUuid(input.getEmployee());
        return objectiveService.createObjective(input, employee);
    }

    public Objective updateObjective(UpdateObjectiveInput input) {
        var employee = employeeService.findEmployeeByUuid(input.getEmployee());
        var objectiveToBeUpdated = objectiveService.findObjectiveByUuid(input.getUuid());
        return objectiveService.updateObjective(input, employee, objectiveToBeUpdated);
    }

    @Override
    public Objective deleteObjective(DeleteObjectiveInput input) {
        var objectiveToBeDeleted = objectiveService.findObjectiveByUuid(input.getUuid());
        return objectiveService.deleteObjective(objectiveToBeDeleted);
    }

    @Override
    public Objective findObjectiveByUuid(UUID uuid){
        return objectiveService.findObjectiveByUuid(uuid);
    }

    @Transactional(readOnly = true)
    public Page<Objective> getObjectives(ObjectivesInput input) {
        return objectiveService.findAllObjectives(input);
    }
}
