package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.VacancyEntity;
import digital.windmill.audra.graphql.type.Vacancy;
import digital.windmill.audra.graphql.type.input.CreateVacancyInput;
import digital.windmill.audra.graphql.type.input.UpdateVacancyInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.Instant;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class, EmployeeMapper.class, EmployeePositionMapper.class})
public interface VacancyMapper {

    @Mapping(target = "id", source = "uuid")
    Vacancy mapVacancyEntityToVacancy(VacancyEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "position", source = "employeePositionEntity")
    @Mapping(target = "description", source = "input.description")
    @Mapping(target = "status", source = "input.status")
    @Mapping(target = "assignTo", source = "employeeEntity")
    @Mapping(target = "priority", source = "input.priority")
    @Mapping(target = "updatedAt", expression = "java(retrieveNowDate())")
    VacancyEntity mapToEntityWhenUpdate(@MappingTarget VacancyEntity vacancyEntity,
                                        UpdateVacancyInput input,
                                        EmployeePositionEntity employeePositionEntity,
                                        EmployeeEntity employeeEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", expression = "java(generateUUID())")
    @Mapping(target = "position", source = "employeePositionEntity")
    @Mapping(target = "description", source = "input.description")
    @Mapping(target = "status", source = "input.status")
    @Mapping(target = "assignTo", source = "employeeEntity")
    @Mapping(target = "priority", source = "input.priority")
    @Mapping(target = "createdAt", expression = "java(retrieveNowDate())")
    @Mapping(target = "updatedAt", expression = "java(retrieveNowDate())")
    VacancyEntity mapInputToEntity(CreateVacancyInput input,
                                   EmployeePositionEntity employeePositionEntity,
                                   EmployeeEntity employeeEntity);

    default Instant retrieveNowDate() {
        return Instant.now();
    }

    default UUID generateUUID() {
        return UUID.randomUUID();
    }

}
