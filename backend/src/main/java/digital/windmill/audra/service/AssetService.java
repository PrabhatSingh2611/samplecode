package digital.windmill.audra.service;

import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.graphql.type.input.AssetsInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface AssetService {
    /**
     * This method will return a specific asset by a specific uuid
     *
     * @param uuid uuid by which we search Asset
     * @return a specific Asset
     */
    Asset findAssetByUuid(UUID uuid);

    /**
     * This method will return all assets
     *
     * @param input input by which we search all the assets.
     * @return list of assets
     */
    Page<Asset> findAll(AssetsInput input);
}
