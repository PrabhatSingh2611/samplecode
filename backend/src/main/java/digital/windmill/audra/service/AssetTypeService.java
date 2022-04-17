package digital.windmill.audra.service;

import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.graphql.type.input.AssetTypeInput;

import java.util.List;
import java.util.UUID;

public interface AssetTypeService {
    /**,
     * This method will return a specific Asset Type by specific UUID.
     * @param uuid uuid by which we search Asset type
     * @return a specific AsserType
     */
    AssetType findAssetByUuid(UUID uuid);

    /**
     * This method will return all the list of Asset type
     * @return List of Asset Type
     */
    List<AssetType> getAssetsType();

    /**
     * This method will create a specific Asset Type by specific input
     * @param assetTypeInput input by which we create Asset type
     * @return created AssetType
     */
    AssetType createAssetType(AssetTypeInput assetTypeInput);
}
