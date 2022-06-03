package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.AssetTypeCategoryEntity;
import digital.windmill.audra.graphql.type.assetTypeCategory.AssetTypeCategory;
import digital.windmill.audra.graphql.type.assetTypeCategory.CreateAssetTypeCategoryInput;
import digital.windmill.audra.graphql.type.assetTypeCategory.PatchAssetTypeCategoryInput;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class})
public interface AssetTypeCategoryMapper {

    @Mapping(target = "id", source = "uuid")
    AssetTypeCategory map(AssetTypeCategoryEntity entity);

    @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
    AssetTypeCategoryEntity map(CreateAssetTypeCategoryInput input);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    AssetTypeCategoryEntity map(PatchAssetTypeCategoryInput input,
                                @MappingTarget AssetTypeCategoryEntity country);

}
