package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.graphql.type.assetType.AssetType;
import digital.windmill.audra.graphql.type.assetType.CreateAssetTypeInput;
import digital.windmill.audra.graphql.type.assetType.UpdateAssetTypeInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.Instant;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class, AssetTypeCategoryMapper.class}, imports = {Instant.class, UUID.class})
public interface AssetTypeMapper {

    @Mapping(target = "id", source = "uuid")
    AssetType map(AssetTypeEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
    @Mapping(target = "name", source = "input.name")
    @Mapping(target = "iconName", source = "input.iconName")
    AssetTypeEntity map(CreateAssetTypeInput input);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "name", source = "input.name")
    @Mapping(target = "iconName", source = "input.iconName")
    AssetTypeEntity map(UpdateAssetTypeInput input,
                        @MappingTarget AssetTypeEntity updatedAssetTypeEntity);

}