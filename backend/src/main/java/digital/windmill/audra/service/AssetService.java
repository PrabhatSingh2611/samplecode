package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.graphql.type.input.AssetsInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface AssetService {

    AssetEntity findAssetByUuid(UUID uuid);

    Page<Asset> findAll(AssetsInput input);

    AssetEntity save(AssetEntity assetEntity);

}
