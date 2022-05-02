package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.ObjectiveEntity;
import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.UpdateObjectiveInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class, EmployeeMapper.class, EmployeePositionMapper.class})
public interface ObjectiveMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", expression = "java(generateUUID())")
    @Mapping(target = "employee", source = "employeeEntity")
    @Mapping(target = "name", source = "input.name")
    @Mapping(target = "description", source = "input.description")
    @Mapping(target = "comments", source = "input.comments")
    @Mapping(target = "dueToDate", expression = "java(dueToDate(input.getDueToDate()))")
    @Mapping(target = "status", source = "input.status")
    ObjectiveEntity mapObjectiveInputToEntity(CreateObjectiveInput input, EmployeeEntity employeeEntity);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "uuid", source = "entity.uuid")
    @Mapping(target = "employee", source = "employeeEntity")
    @Mapping(target = "name", source = "input.name")
    @Mapping(target = "description", source = "input.description")
    @Mapping(target = "comments", source = "input.comments")
    @Mapping(target = "dueToDate", expression = "java(dueToDate(input.getDueToDate()))")
    @Mapping(target = "status", source = "input.status")
    ObjectiveEntity mapInputToEntityWhenUpdate(UpdateObjectiveInput input, ObjectiveEntity entity, EmployeeEntity employeeEntity);

    @Mapping(target = "employee.reportingManager", ignore = true)
    Objective mapObjectiveEntityToObjective(ObjectiveEntity objectiveEntity);

    @Mapping(target = "dueToDate", expression = "java(dueToDate(objective.getDueToDate()))")
    ObjectiveEntity mapObjectiveToObjectiveEntity(Objective objective);

    default UUID generateUUID() {
        return UUID.randomUUID();
    }

    default Instant dueToDate(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toInstant();
    }
}
