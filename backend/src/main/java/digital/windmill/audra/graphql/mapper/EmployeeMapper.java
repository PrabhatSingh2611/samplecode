package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

import java.util.Optional;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class})
public interface EmployeeMapper {

    @Mapping(target = "reportingManager.reportingManager", ignore = true)
    Employee map(EmployeeEntity entity);

    default String map(EmployeePositionEntity position) {
        return Optional.ofNullable(position).map(EmployeePositionEntity::getName).orElse(null);
    }


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reportingManager.reportingManager", ignore = true)
    @Mapping(target = "uuid", expression = "java(generateUUID())")
    @Mapping(target = "firstName", source = "input.firstName")
    @Mapping(target = "lastName", source = "input.lastName")
    @Mapping(target = "role", source = "input.role")
    @Mapping(target = "birthday", source = "input.birthday")
    @Mapping(target = "reportingManager", source = "employeeEntity")
    @Mapping(target = "position", source = "employeePositionEntity")
    @Mapping(target = "location", source = "locationEntity")
    EmployeeEntity map(CreateEmployeeInput input,
                       EmployeeEntity employeeEntity,
                       EmployeePositionEntity employeePositionEntity,
                       LocationEntity locationEntity);

    default UUID generateUUID() {
        return UUID.randomUUID();
    }
}
