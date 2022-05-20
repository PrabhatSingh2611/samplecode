package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.graphql.type.input.ArchiveAssetInput;
import digital.windmill.audra.graphql.type.input.CreateAssetInput;
import digital.windmill.audra.graphql.type.input.UpdateAssetInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.Instant;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class, EmployeeMapper.class}, imports = {Instant.class})
public interface AssetMapper {

    /**
     * This Map AssetEntity to Asset
     *
     * @param entity it takes AssetEntity
     * @return mapped Asset
     */

    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "type.id", source = "type.uuid")
    @Mapping(target = "location.id", source = "location.uuid")
    Asset mapAssetEntityToAsset(AssetEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", expression = "java(generateUUID())")
    @Mapping(target = "title", source = "input.title")
    @Mapping(target = "serialNumber", source = "input.serialNumber")
    @Mapping(target = "type", source = "assetTypeEntity")
    @Mapping(target = "assignee", source = "employeeEntity")
    @Mapping(target = "archivedDate", source = "input.archivedDate")
    @Mapping(target = "waybillDate", source = "input.waybillDate")
    @Mapping(target = "nextActionDate", source = "input.nextActionDate")
    @Mapping(target = "actionOnName", source = "input.actionOnName")
    @Mapping(target = "comment", source = "input.comment")
    @Mapping(target = "location", source = "locationEntity")
    AssetEntity mapAssetCreateInputToAssetEntity(CreateAssetInput input,
                                                 AssetTypeEntity assetTypeEntity,
                                                 EmployeeEntity employeeEntity,
                                                 LocationEntity locationEntity);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "title", source = "input.title")
    @Mapping(target = "serialNumber", source = "input.serialNumber")
    @Mapping(target = "type", source = "assetTypeEntity")
    @Mapping(target = "assignee", source = "employeeEntity")
    @Mapping(target = "archivedDate", source = "input.archivedDate")
    @Mapping(target = "waybillDate", source = "input.waybillDate")
    @Mapping(target = "actionOnName", source = "input.actionOnName")
    @Mapping(target = "nextActionDate", source = "input.nextActionDate")
    @Mapping(target = "location", source = "locationEntity")
    @Mapping(target = "comment", source = "input.comment")
    AssetEntity mapAssetUpdateInputToAssetEntity(@MappingTarget AssetEntity assetEntity,
                                                 UpdateAssetInput input,
                                                 AssetTypeEntity assetTypeEntity,
                                                 EmployeeEntity employeeEntity,
                                                 LocationEntity locationEntity);

    default UUID generateUUID() {
        return UUID.randomUUID();
    }


    @Mapping(target = "archivedDate", expression = "java(Instant.now())")
    AssetEntity mapArchiveAssetInputToAssetEntity(ArchiveAssetInput input, @MappingTarget AssetEntity assetEntity);

}
