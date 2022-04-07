package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.graphql.type.EmployeePosition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeePositionMapper {
    EmployeePosition map(EmployeePositionEntity entity);
}
