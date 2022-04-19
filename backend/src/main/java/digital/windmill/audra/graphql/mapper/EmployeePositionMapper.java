package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.input.CreateEmployeePositionInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class, EmployeePositionMapper.class})
public interface EmployeePositionMapper {

    /**It maps CreateEmployeePositionInput to EmployeePositionEntity
     * @param input it used for receiving basic information of employeePosition like id, uuid and name
     * @return mapped EmployeePositionEntity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", expression = "java(generateUUID())")
    @Mapping(target = "name", source = "input.name")
    EmployeePositionEntity mapCreateEmployeePositionInputToEmployeePositionEntity(CreateEmployeePositionInput input);

    /**Maps EmployeePositionEntity To EmployeePosition
     * @param employeePositionEntity EmployeePositionEntity
     * @return mapped EmployeePosition
     */
    EmployeePosition mapEmployeePositionEntityToEmployeePosition(EmployeePositionEntity employeePositionEntity);

    /**Maps EmployeePosition To EmployeePositionEntity
     * @param employeePosition EmployeePosition
     * @return mapped EmployeePositionEntity
     */
    EmployeePositionEntity mapEmployeePositionToEmployeePositionEntity(EmployeePosition employeePosition);

    default UUID generateUUID() {
        return UUID.randomUUID();
    }
}

