package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.graphql.facade.AssetFacade;
import digital.windmill.audra.graphql.mapper.AssetMapper;
import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.graphql.type.input.ArchiveAssetInput;
import digital.windmill.audra.graphql.type.input.AssetsInput;
import digital.windmill.audra.graphql.type.input.CreateAssetInput;
import digital.windmill.audra.graphql.type.input.UpdateAssetInput;
import digital.windmill.audra.service.AssetService;
import digital.windmill.audra.service.AssetTypeService;
import digital.windmill.audra.service.EmployeeService;
import digital.windmill.audra.service.impl.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AssetFacadeImpl implements AssetFacade {

    private AssetService assetService;
    private AssetTypeService assetTypeService;
    private EmployeeService employeeService;
    private LocationService locationService;
    private AssetMapper assetMapper;

    @Override
    @Transactional(readOnly = true)
    public Asset findAssetByUuid(UUID uuid) {
        return assetMapper.mapAssetEntityToAsset(assetService.findAssetByUuid(uuid));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Asset> findAllAssets(AssetsInput input) {
        return assetService.findAll(input)
                .map(assetMapper::mapAssetEntityToAsset);
    }

    @Override
    @Transactional
    public Asset createAsset(CreateAssetInput input) {
        var assetTypeEntity = assetTypeService.findAssetByUuid(input.getType().getId());

        var employeeEntity = input.getAssignee() != null ?
                employeeService.findEmployeeByUuid(input.getAssignee().getId()) : null;

        var locationEntity = input.getLocation() != null ?
                locationService.findLocationByUuid(input.getLocation().getId()) : null;

        var entity = assetMapper.mapAssetCreateInputToAssetEntity(input, assetTypeEntity, employeeEntity, locationEntity);
        return assetMapper.mapAssetEntityToAsset(assetService.createOrUpdateAsset(entity));
    }

    @Override
    @Transactional
    public Asset updateAsset(UpdateAssetInput input) {
        var assetEntity = assetService.findAssetByUuid(input.getId());
        var assetTypeEntity = assetTypeService.findAssetByUuid(input.getType().getId());
        var employeeEntity = input.getAssignee() != null ?
                employeeService.findEmployeeByUuid(input.getAssignee().getId())
                : null;
        var locationEntity = input.getLocation() != null ?
                locationService.findLocationByUuid(input.getLocation().getId())
                : null;
        var entity = assetMapper.mapAssetUpdateInputToAssetEntity(assetEntity,input, assetTypeEntity, employeeEntity, locationEntity);
        return assetMapper.mapAssetEntityToAsset(assetService.createOrUpdateAsset(entity));
    }

    @Override
    @Transactional
    public Asset updateAssetAsArchive(ArchiveAssetInput input) {
        var uuid = input.getAsset().getId();
        AssetEntity assetEntity = assetService.findAssetByUuid(uuid);
        return assetMapper
                .mapAssetEntityToAsset(assetService
                        .createOrUpdateAsset(assetMapper
                                .mapArchiveAssetInputToAssetEntity(input, assetEntity)));
    }
}
