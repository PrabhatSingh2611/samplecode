package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.ObjectiveEntity;
import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.UpdateObjectiveInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Mapper(componentModel = "spring" ,uses = {DateTimeMapper.class})
public interface ObjectiveMapper {
    /*@Mapping(target = "employee.reportingManager", ignore = true)
    Objective map(ObjectiveEntity entity);*/


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", expression = "java(generateUUID())")
    @Mapping(target = "employee", source = "employeeEntity")
    @Mapping(target = "name", source = "input.name")
    @Mapping(target = "description", source = "input.description")
    @Mapping(target = "comments", source = "input.comments")
    @Mapping(target = "dueToDate", expression = "java(dueToDate(input.getDueToDate()))")
    @Mapping(target="status",source = "input.status")
    ObjectiveEntity mapInputToEntity(CreateObjectiveInput input, EmployeeEntity employeeEntity);

    @Mapping(target = "employee.reportingManager" , ignore = true)
    Objective map(ObjectiveEntity objectiveEntity);

    default String map(EmployeePositionEntity position) {
        return Optional.ofNullable(position).map(EmployeePositionEntity::getName).orElse(null);
    }

    default UUID generateUUID() {
        return UUID.randomUUID();
    }

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "uuid",ignore = true)
    @Mapping(target = "employee", source = "employeeEntity")
    @Mapping(target = "name", source = "input.name")
    @Mapping(target = "description", source = "input.description")
    @Mapping(target = "comments", source = "input.comments")
    @Mapping(target = "dueToDate", expression = "java(dueToDate(input.getDueToDate()))")
    @Mapping(target="status",source = "input.status")
    ObjectiveEntity mapInputToEntityWhenUpdate(UpdateObjectiveInput input,
                                               ObjectiveEntity entity,
                                               EmployeeEntity employeeEntity);

    default Instant dueToDate(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toInstant();
    }
}
