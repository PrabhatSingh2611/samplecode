package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.graphql.type.input.CreateAssetInput;
import digital.windmill.audra.graphql.type.input.UpdateAssetInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class, EmployeeMapper.class, AssetTypeMapper.class})
public interface AssetMapper {

    /**
     * This Map AssetEntity to Asset
     *
     * @param entity it takes AssetEntity
     * @return mapped Asset
     */
    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "serial", source = "serialNumber")
    Asset mapAssetEntityToAsset(AssetEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", expression = "java(generateUUID())")
    @Mapping(target = "title", source = "input.title")
    @Mapping(target = "serialNumber", source = "input.serial")
    @Mapping(target = "type", source = "assetTypeEntity")
    @Mapping(target = "employee", source = "employeeEntity")
    @Mapping(target = "archivedDate", source = "input.archivedDate")
    @Mapping(target = "purchasedDate", source = "input.purchasedDate")
    @Mapping(target = "nextActionDate", source = "input.nextActionDate")
    AssetEntity mapAssetCreateInputToAssetEntity(CreateAssetInput input,
                                                 AssetTypeEntity assetTypeEntity,
                                                 EmployeeEntity employeeEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "title", source = "input.title")
    @Mapping(target = "serialNumber", source = "input.serial")
    @Mapping(target = "type", source = "assetTypeEntity")
    @Mapping(target = "employee", source = "employeeEntity")
    @Mapping(target = "archivedDate", source = "input.archivedDate")
    @Mapping(target = "purchasedDate", source = "input.purchasedDate")
    @Mapping(target = "nextActionDate", source = "input.nextActionDate")
    AssetEntity mapAssetUpdateInputToAssetEntity(UpdateAssetInput input,
                                                 @MappingTarget AssetEntity assetEntity,
                                                 AssetTypeEntity assetTypeEntity,
                                                 EmployeeEntity employeeEntity);

    default UUID generateUUID() {
        return UUID.randomUUID();
    }
}
