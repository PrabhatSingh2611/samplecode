package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.ObjectiveEntity;
import digital.windmill.audra.dao.repository.EmployeeRepository;
import digital.windmill.audra.dao.repository.ObjectiveRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.ObjectiveMapper;
import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.UpdateObjectiveInput;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ObjectiveService {

    private ObjectiveRepository objectiveRepository;
    private EmployeeRepository employeeRepository;
    private ObjectiveMapper objectiveMapper;

    public Objective createObjective(CreateObjectiveInput input, EmployeeEntity employeeEntity){
        ObjectiveEntity objectiveEntity = objectiveMapper.mapInputToEntity(input,employeeEntity);
        return objectiveMapper.map(objectiveRepository.save(objectiveEntity));
    }

    public Objective updateObjective(UpdateObjectiveInput input,EmployeeEntity employeeEntity){
        ObjectiveEntity entity = objectiveRepository.findByUuid(input.getUuid())
                .orElseThrow(
                        () -> new DataNotFoundException("Objective not found.")
                );

       ObjectiveEntity objectiveEntity = objectiveMapper.mapInputToEntityWhenUpdate(input,entity,employeeEntity);
       return objectiveMapper.map(objectiveRepository.save(objectiveEntity));
    }


}
