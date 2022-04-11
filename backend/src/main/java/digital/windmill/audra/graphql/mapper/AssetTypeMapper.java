package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.graphql.type.AssetType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssetTypeMapper {

    AssetType map(AssetTypeEntity entity);

}