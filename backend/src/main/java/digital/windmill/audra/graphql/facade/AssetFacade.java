package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.graphql.type.input.AssetsInput;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface AssetFacade {
    /**
     * This method will return a specific asset by a specific uuid
     *
     * @param uuid uuid by which we search Asset
     * @return a specific Asset
     */
    @Transactional(readOnly = true)
    Asset findAssetByUuid(UUID uuid);

    /**
     * This method will return all assets
     *
     * @param input input by which we search all the assets.
     * @return list of assets
     */
    @Transactional(readOnly = true)
    Page<Asset> findAllAssets(AssetsInput input);
}
