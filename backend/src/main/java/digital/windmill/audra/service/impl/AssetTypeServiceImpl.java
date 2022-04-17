package digital.windmill.audra.service.impl;


import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.dao.repository.AssetTypeRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.AssetTypeMapper;
import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.graphql.type.input.AssetTypeInput;
import digital.windmill.audra.service.AssetTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AssetTypeServiceImpl implements AssetTypeService {

    private AssetTypeRepository assetTypeRepository;
    private AssetTypeMapper assetTypeMapper;

    @Override public AssetTypeEntity findByUuid(UUID uuid) {
        return assetTypeRepository.findByUuid(uuid).orElseThrow(
                () -> new DataNotFoundException("Asset Type not found"));
    }

    @Override public List<AssetTypeEntity> getAssetsType() {
        return assetTypeRepository.findAll();
    }

    @Override public AssetType createAssetType(AssetTypeInput input) {

        AssetTypeEntity savedAssetEntity = assetTypeRepository.save(assetTypeMapper.mapAssetTypeInputToAssetTypeEntity(input));
        return assetTypeMapper.mapAssetTypeEntityToAssetType(savedAssetEntity);

    }

}
