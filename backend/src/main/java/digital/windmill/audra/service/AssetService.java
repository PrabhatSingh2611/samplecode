package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.graphql.type.input.AssetsInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface AssetService {
    /**
     * This method will return a specific asset by a specific uuid
     *
     * @param uuid uuid by which we search Asset
     * @return a specific AssetEntity
     */
    AssetEntity findAssetByUuid(UUID uuid);

    /**
     * This method will return all assets
     *
     * @param input input by which we search all the assets.
     * @return list of assets
     */
    Page<AssetEntity> findAll(AssetsInput input);

    /**
     * This method will create or update a specific Asset by specific input
     *
     * @param entity entity by which we create Asset
     * @return created AssetEntity
     */
    AssetEntity createOrUpdateAsset(AssetEntity entity);

}
