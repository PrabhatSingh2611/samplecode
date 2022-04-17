package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.graphql.type.input.AssetTypeInput;

import java.util.List;
import java.util.UUID;

public interface AssetTypeService {
    /**It Look for AssetTypeEntity in database
     * @param uuid input will be as uuid which will be used for looking in database
     * @return a matched AssetTypeEntity in database
     */
    AssetTypeEntity findByUuid(UUID uuid);

    List<AssetTypeEntity> getAssetsType();

    /**It will be used to create AssetType
     * @param input takes necessary for creating AssetTypeEntity
     * @return created AssetType
     */
    AssetType createAssetType(AssetTypeInput input);
}
