package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.graphql.type.Employee;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class})
public interface EmployeeMapper {

    Employee map(EmployeeEntity entity);

    default String map(EmployeePositionEntity position) {
        return Optional.ofNullable(position).map(EmployeePositionEntity::getName).orElse(null);
    }

}
