package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.AssetFacade;
import digital.windmill.audra.graphql.mapper.AssetMapper;
import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.graphql.type.input.AssetsInput;
import digital.windmill.audra.graphql.type.input.CreateAssetInput;
import digital.windmill.audra.graphql.type.input.UpdateAssetInput;
import digital.windmill.audra.service.AssetService;
import digital.windmill.audra.service.AssetTypeService;
import digital.windmill.audra.service.EmployeeService;
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
    private AssetMapper assetMapper;

    @Override
    @Transactional(readOnly = true)
    public Asset findAssetByUuid(UUID uuid) {
        return assetMapper.mapAssetEntityToAsset(assetService.findAssetByUuid(uuid));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Asset> findAllAssets(AssetsInput input) {
        return assetService.findAll(input);
    }

    @Override
    public Asset createAsset(CreateAssetInput input) {
        var assetTypeEntity = assetTypeService.findAssetByUuid(input.getType());
        var employeeEntity = employeeService.findEmployeeByUuid(input.getEmployee());
        var entity = assetMapper.mapAssetCreateInputToAssetEntity(input, assetTypeEntity, employeeEntity);
        return assetMapper.mapAssetEntityToAsset(assetService.createOrUpdateAsset(entity));
    }

    @Override
    public Asset updateAsset(UpdateAssetInput input) {
        var assetEntity = assetService.findAssetByUuid(input.getUuid());
        var assetTypeEntity = assetTypeService.findAssetByUuid(input.getType());
        var employeeEntity = employeeService.findEmployeeByUuid(input.getEmployee());
        var entity = assetMapper.mapAssetUpdateInputToAssetEntity(input, assetEntity, assetTypeEntity, employeeEntity);
        return assetMapper.mapAssetEntityToAsset(assetService.createOrUpdateAsset(entity));
    }
}
