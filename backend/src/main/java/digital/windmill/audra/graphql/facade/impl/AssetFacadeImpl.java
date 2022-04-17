package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.AssetFacade;
import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.graphql.type.input.AssetsInput;
import digital.windmill.audra.service.impl.AssetServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AssetFacadeImpl implements AssetFacade {

    private AssetServiceImpl assetServiceImpl;

    @Override
    @Transactional(readOnly = true)
    public Asset findAssetByUuid(UUID uuid) {
        return assetServiceImpl.findAssetByUuid(uuid);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Asset> findAllAssets(AssetsInput input) {
        return assetServiceImpl.findAll(input);
    }
}
