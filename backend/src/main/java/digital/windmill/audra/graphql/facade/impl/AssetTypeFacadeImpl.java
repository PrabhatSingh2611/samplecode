package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.AssetTypeFacade;
import digital.windmill.audra.graphql.mapper.AssetTypeMapper;
import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.graphql.type.input.AssetTypeInput;
import digital.windmill.audra.service.impl.AssetTypeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AssetTypeFacadeImpl implements AssetTypeFacade {

    private AssetTypeServiceImpl assetTypeServiceImpl;
    private AssetTypeMapper assetTypeMapper;

    @Override
    @Transactional(readOnly = true)
    public AssetType findAssetTypeByUuid(UUID uuid) {
        return assetTypeServiceImpl.findAssetByUuid(uuid);
    }

    @Override
    public AssetType createAssetType(AssetTypeInput assetTypeInput){
        return assetTypeMapper.map(assetTypeServiceImpl.createAssetType(assetTypeInput));
    }

    @Override
    public List<AssetType> getAssetsType() {
        return assetTypeServiceImpl
                .getAssetsType();
    }
}