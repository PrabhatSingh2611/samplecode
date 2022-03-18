package digital.windmill.audra.graphql.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeeLocationEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.graphql.type.Employee;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class})
public interface EmployeeMapper {

    Employee map(EmployeeEntity entity);
    
    default String map(EmployeePositionEntity position) {
        return Optional.ofNullable(position).map(EmployeePositionEntity::getName).orElse(null);
    }

    default String map(EmployeeLocationEntity position) {
        return Optional.ofNullable(position).map(EmployeeLocationEntity::getName).orElse(null);
    }

}
