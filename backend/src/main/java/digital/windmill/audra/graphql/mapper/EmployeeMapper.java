package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import digital.windmill.audra.graphql.type.input.UpdateEmployeeInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class}, uses = {DateTimeMapper.class, EmployeeMapper.class, EmployeePositionMapper.class, LocationMapper.class})
public interface EmployeeMapper {

    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "reportingManager.id", source = "reportingManager.uuid")
    @Mapping(target = "reportingManager.reportingManager", ignore = true)
    Employee mapEmployeeEntityToEmployee(EmployeeEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
    @Mapping(target = "firstName", source = "input.firstName")
    @Mapping(target = "lastName", source = "input.lastName")
    @Mapping(target = "role", source = "input.role")
    @Mapping(target = "birthday", source = "input.birthday")
    @Mapping(target = "reportingManager", source = "reportingManagerEntity")
    @Mapping(target = "position", source = "employeePositionEntity")
    @Mapping(target = "location", source = "locationEntity")
    EmployeeEntity mapEmployeeInputToEmployeeEntity(CreateEmployeeInput input,
                                                    EmployeeEntity reportingManagerEntity,
                                                    EmployeePositionEntity employeePositionEntity,
                                                    LocationEntity locationEntity);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "firstName", source = "input.firstName")
    @Mapping(target = "lastName", source = "input.lastName")
    @Mapping(target = "role", source = "input.role")
    @Mapping(target = "birthday", source = "input.birthday")
    @Mapping(target = "reportingManager", expression = "java(reportingManagerEntity)")
    @Mapping(target = "location", expression = "java(locationEntity)")
    @Mapping(target = "position", expression = "java(employeePositionEntity)")
    EmployeeEntity mapUpdateEmployeeInputToEmployeeEntity(UpdateEmployeeInput input,
                                                        @MappingTarget EmployeeEntity entity,
                                                        EmployeeEntity reportingManagerEntity,
                                                        EmployeePositionEntity employeePositionEntity,
                                                        LocationEntity locationEntity);

}
