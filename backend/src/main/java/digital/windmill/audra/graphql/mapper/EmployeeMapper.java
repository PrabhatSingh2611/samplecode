package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class, EmployeeMapper.class, EmployeePositionMapper.class})
public interface EmployeeMapper {

    /**
     * It maps EmployeeEntity to Employee
     *
     * @param entity input as EmployeeEntity
     * @return output as Employee
     */
    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "reportingManager.id", source = "reportingManager.uuid")
    @Mapping(target = "reportingManager.location.id", source = "reportingManager.location.uuid")
    @Mapping(target = "reportingManager.reportingManager", ignore = true)
    @Mapping(target = "location.id", source = "location.uuid")
    Employee mapEmployeeEntityToEmployee(EmployeeEntity entity);

    default String map(EmployeePositionEntity position) {
        return Optional.ofNullable(position).map(EmployeePositionEntity::getName).orElse(null);
    }

    /**
     * It maps CreateEmployeeInput to EmployeeEntity
     *
     * @param input                  it used for receiving basic information of employee like firstName, lastName, birthday, etc
     * @param reportingManagerInput  it takes manager's information of employee to be mapped
     * @param employeePositionEntity position of employee that will be mapped
     * @param locationEntity         location of employee that will be mapped
     * @return mapped EmployeeEntity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reportingManager.reportingManager", ignore = true)
    @Mapping(target = "uuid", expression = "java(generateUUID())")
    @Mapping(target = "firstName", source = "input.firstName")
    @Mapping(target = "lastName", source = "input.lastName")
    @Mapping(target = "role", source = "input.role")
    @Mapping(target = "birthday", source = "input.birthday")
    @Mapping(target = "reportingManager", source = "reportingManagerInput")
    @Mapping(target = "position", source = "employeePositionEntity")
    @Mapping(target = "location", source = "locationEntity")
    EmployeeEntity mapEmployeeInputToEmployeeEntity(CreateEmployeeInput input,
                                                    EmployeeEntity reportingManagerInput,
                                                    EmployeePositionEntity employeePositionEntity,
                                                    LocationEntity locationEntity);

    default UUID generateUUID() {
        return UUID.randomUUID();
    }

}
