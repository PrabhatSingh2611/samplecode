package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.graphql.type.input.CreateAssetTypeInput;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface AssetTypeService {
    /**
     * ,
     * This method will return a specific Asset Type by specific UUID.
     *
     * @param uuid uuid by which we search Asset type
     * @return a specific AsserType
     */
    AssetType findAssetByUuid(UUID uuid);

    /**
     * This method will return all the list of Asset type
     *
     * @return List of Asset Type
     */
    Page<AssetType> getAssetsType();

    /**
     * This method will create a specific Asset Type by specific input
     *
     * @param createAssetTypeInput input by which we create Asset type
     * @return created AssetType
     */
    AssetTypeEntity createAssetType(CreateAssetTypeInput createAssetTypeInput);
}
