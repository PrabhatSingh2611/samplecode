package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.AssetTypeFacade;
import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.graphql.type.input.CreateAssetTypeInput;
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

    @Override
    @Transactional(readOnly = true)
    public AssetType findAssetTypeByUuid(UUID uuid) {
        return assetTypeService.findAssetByUuid(uuid);
    }

    @Override
    public AssetType createAssetType(CreateAssetTypeInput createAssetTypeInput) {
        return assetTypeService.createAssetType(createAssetTypeInput);
    }

    @Override
    public List<AssetType> getAssetsType() {
        return assetTypeService
                .getAssetsType();
    }
}