package digital.windmill.audra.graphql.mapper;

import org.mapstruct.Mapper;

import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.graphql.type.AssetType;

@Mapper(componentModel = "spring",uses = {DateTimeMapper.class, EmployeeMapper.class})
public interface AssetTypeMapper {

    AssetType map(AssetTypeEntity entity);

}
