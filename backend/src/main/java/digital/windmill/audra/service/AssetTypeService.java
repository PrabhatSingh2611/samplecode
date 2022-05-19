package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.AssetTypeEntity;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface AssetTypeService {

    AssetTypeEntity findAssetByUuid(UUID uuid);

    Page<AssetTypeEntity> getAssetsType();

    AssetTypeEntity save(AssetTypeEntity assetTypeEntity);
}
