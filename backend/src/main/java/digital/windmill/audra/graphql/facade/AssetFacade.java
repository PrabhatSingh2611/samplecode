package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.graphql.type.input.ArchiveAssetInput;
import digital.windmill.audra.graphql.type.input.AssetsInput;
import digital.windmill.audra.graphql.type.input.CreateAssetInput;
import digital.windmill.audra.graphql.type.input.UpdateAssetInput;
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


    /**
     * This method will create a new asset by a value.
     *
     * @param input of which asset will be created
     * @return a new created asset
     */
    Asset createAsset(CreateAssetInput input);


    /**
     * This method will update asset by a value.
     *
     * @param input of which asset will be updated
     * @return an updated asset
     */
    Asset updateAsset(UpdateAssetInput input);

    /**
     * This method will save asset as archive
     * @param input will take asset to be marked as archived
     * @return an updated asset
     */
    Asset updateAssetAsArchive(ArchiveAssetInput input);
}
