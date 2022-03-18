package digital.windmill.audra.graphql.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.graphql.type.Asset;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class, EmployeeMapper.class})
public interface AssetMapper {

    Asset map(AssetEntity entity);

}
