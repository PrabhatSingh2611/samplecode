package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.graphql.type.Asset;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class, EmployeeMapper.class})
public interface AssetMapper {

    @Mapping(target = "serial", source = "serialNumber")
    Asset map(AssetEntity entity);
}
