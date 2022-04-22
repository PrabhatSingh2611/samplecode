package digital.windmill.audra.service.impl;


import digital.windmill.audra.dao.AssetTypeSpecification;
import digital.windmill.audra.dao.repository.AssetTypeRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.AssetTypeMapper;
import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.graphql.type.input.CreateAssetTypeInput;
import digital.windmill.audra.service.AssetTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class AssetTypeServiceImpl implements AssetTypeService {

    private AssetTypeRepository assetTypeRepository;
    private AssetTypeMapper assetTypeMapper;

    @Override
    public AssetType findAssetByUuid(UUID uuid) {
        return assetTypeMapper.
                mapAssetTypeEntityToAssetType(assetTypeRepository.findByUuid(uuid).orElseThrow(
                        () -> new DataNotFoundException("Asset Type not found")));
    }

    @Override
    public Page<AssetType> getAssetsType() {
        var spec = AssetTypeSpecification.assetTypes();
        return assetTypeRepository
                .findAll(spec.getKey()
                        ,spec.getValue())
                .map(assetTypeMapper::mapAssetTypeEntityToAssetType);
    }

    @Override
    public AssetType createAssetType(CreateAssetTypeInput input) {
        var savedAssetEntity = assetTypeRepository
                .save(assetTypeMapper.mapAssetTypeInputToAssetTypeEntity(input));
        return assetTypeMapper.mapAssetTypeEntityToAssetType(savedAssetEntity);

    }

}
