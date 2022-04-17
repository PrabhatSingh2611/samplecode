package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.graphql.type.input.AssetTypeInput;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface AssetTypeFacade {
    @Transactional(readOnly = true)
    AssetType findAssetTypeByUuid(UUID uuid);

    /**It updates AssetType Entity
     * @param assetTypeInput is takes a value to update AssetType Entity
     * @return an updated AssetType
     */
    AssetType createAssetType(AssetTypeInput assetTypeInput);

    List<AssetType> getAssetsType();
}
