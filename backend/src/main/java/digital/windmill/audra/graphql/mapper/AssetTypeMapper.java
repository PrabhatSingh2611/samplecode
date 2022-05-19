package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.graphql.type.input.CreateAssetTypeInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AssetTypeMapper {

    /**
     * It maps AssetTypeEntity to AssetType
     *
     * @param entity takes AssetTypeEntity as input
     * @return mapped AssetType
     */
    @Mapping(target = "id", source = "uuid")
    AssetType mapAssetTypeEntityToAssetType(AssetTypeEntity entity);

    /**
     * It maps AssetTypeInput to AssetTypeEntity
     *
     * @param input takes AssetTypeInput as input
     * @return mapped AssetTypeEntity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", expression = "java(generateUUID())")
    @Mapping(target = "title", source = "input.title")
    AssetTypeEntity mapAssetTypeInputToAssetTypeEntity(CreateAssetTypeInput input);

    default UUID generateUUID() {
        return UUID.randomUUID();
    }
}