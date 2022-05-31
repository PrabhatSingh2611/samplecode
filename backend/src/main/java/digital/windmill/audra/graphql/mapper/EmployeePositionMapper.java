package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.input.CreateEmployeePositionInput;
import digital.windmill.audra.graphql.type.input.UpdateEmployeePositionInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class, EmployeePositionMapper.class})
public interface EmployeePositionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", expression = "java(generateUUID())")
    @Mapping(target = "name", source = "input.name")
    EmployeePositionEntity mapCreateEmployeePositionInputToEmployeePositionEntity(CreateEmployeePositionInput input);

    @Mapping(target = "id", source = "uuid")
    EmployeePosition mapEmployeePositionEntityToEmployeePosition(EmployeePositionEntity employeePositionEntity);

    @Mapping(target = "id", ignore = true)
    EmployeePositionEntity mapUpdateToEmployeePositionEntity(@MappingTarget EmployeePositionEntity employeePositionEntity, UpdateEmployeePositionInput input);

    default UUID generateUUID() {
        return UUID.randomUUID();
    }
}

