package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.AssetSpecification;
import digital.windmill.audra.dao.repository.AssetRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.AssetMapper;
import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.graphql.type.input.AssetsInput;
import digital.windmill.audra.service.AssetService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class AssetServiceImpl implements AssetService {

    private AssetRepository assetRepository;
    private AssetMapper assetMapper;

    @Override
    public Asset findAssetByUuid(UUID uuid) {
        return assetMapper.mapAssetEntityToAsset(assetRepository.findByUuid(uuid).orElseThrow(
                () -> new DataNotFoundException("Asset not found.")
        ));
    }

    @Override
    public Page<Asset> findAll(AssetsInput input) {
        var spec = AssetSpecification.assets(input);
        return assetRepository.findAll(spec.getKey(), spec.getValue()).map(assetMapper::mapAssetEntityToAsset);
    }
}