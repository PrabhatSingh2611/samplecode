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
import java.util.stream.Collectors;

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
    public List<AssetType> getAssetsType() {

        return assetTypeRepository
                .findAll()
                .stream()
                .map(assetTypeMapper::mapAssetTypeEntityToAssetType)
                .collect(Collectors.toList());
    }

    @Override
    public AssetType createAssetType(AssetTypeInput input) {

        AssetTypeEntity savedAssetEntity = assetTypeRepository.save(assetTypeMapper.mapAssetTypeInputToAssetTypeEntity(input));
        return assetTypeMapper.mapAssetTypeEntityToAssetType(savedAssetEntity);

    }

}
