package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.graphql.type.input.CreateAssetTypeInput;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface AssetTypeFacade {
    /**
     * ,
     * This method will return a specific Asset Type by specific UUID.
     *
     * @param uuid uuid by which we search Asset type
     * @return a specific AsserType
     */
    @Transactional(readOnly = true)
    AssetType findAssetTypeByUuid(UUID uuid);

    /**
     * This method will create a specific Asset Type by specific input
     *
     * @param createAssetTypeInput input by which we create Asset type
     * @return created AssetType
     */
    AssetType createAssetType(CreateAssetTypeInput createAssetTypeInput);

    /**
     * This method will return all the list of Asset type
     *
     * @return List of Assettype
     */
    List<AssetType> getAssetsType();
}