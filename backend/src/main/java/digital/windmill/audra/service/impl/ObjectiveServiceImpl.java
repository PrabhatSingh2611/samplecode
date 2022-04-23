package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.entity.ObjectiveEntity;
import digital.windmill.audra.dao.repository.ObjectiveRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.mapper.ObjectiveMapper;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.UpdateObjectiveInput;
import digital.windmill.audra.service.ObjectiveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class ObjectiveServiceImpl implements ObjectiveService {

    private ObjectiveRepository objectiveRepository;
    private ObjectiveMapper objectiveMapper;
    private EmployeeMapper employeeMapper;

    @Override
    public Objective createObjective(CreateObjectiveInput input, Employee employee) {
        ObjectiveEntity objectiveEntity = objectiveMapper
                .mapObjectiveInputToEntity(input, employeeMapper.mapEmployeeToEmployeeEntity(employee));
        return objectiveMapper.mapObjectiveEntityToObjective(objectiveRepository.save(objectiveEntity));
    }

    @Override
    public Objective updateObjective(UpdateObjectiveInput input, Employee employee, Objective objectiveToBeUpdated) {

        ObjectiveEntity objectiveEntity = objectiveMapper.mapInputToEntityWhenUpdate(
                input,
                objectiveMapper.mapObjectiveToObjectiveEntity(objectiveToBeUpdated),
                employeeMapper.mapEmployeeToEmployeeEntity(employee));
        return objectiveMapper.mapObjectiveEntityToObjective(objectiveRepository.save(objectiveEntity));
    }

    @Override
    public Objective findObjectiveByUuid(UUID uuid) {
        return objectiveMapper.mapObjectiveEntityToObjective(objectiveRepository.findByUuid(uuid).orElseThrow(
                        () -> new DataNotFoundException("Objective not available for given UUID : " + uuid.toString())
                )
        );
    }
}
