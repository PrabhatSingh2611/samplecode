package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.AssetTypeFacade;
import digital.windmill.audra.graphql.mapper.AssetTypeMapper;
import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.graphql.type.input.AssetTypeInput;
import digital.windmill.audra.service.AssetTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AssetTypeFacadeImpl implements AssetTypeFacade {

    private AssetTypeService assetTypeService;
    private AssetTypeMapper assetTypeMapper;

    @Override@Transactional(readOnly = true)
    public AssetType findAssetTypeByUuid(UUID uuid) {
        return assetTypeMapper.mapAssetTypeEntityToAssetType(assetTypeService.findByUuid(uuid));
    }

    @Override public AssetType createAssetType(AssetTypeInput assetTypeInput){
        return assetTypeService.createAssetType(assetTypeInput);
    }

    @Override public List<AssetType> getAssetsType() {
        return assetTypeService
                .getAssetsType()
                .stream()
                .map(x -> assetTypeMapper.mapAssetTypeEntityToAssetType(x))
                .toList();
    }
}