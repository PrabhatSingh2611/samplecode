package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.graphql.type.EmployeePosition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeePositionMapper {
    /**Map EmployeePositionEntity to EmployeePosition
     * @param entity takes EmployeePositionEntity
     * @return mapped EmployeePosition
     */
    EmployeePosition mapEmployeePositionEntityToEmployeePosition(EmployeePositionEntity entity);
}
