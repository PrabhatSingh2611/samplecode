package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.AssetTypeFacade;
import digital.windmill.audra.graphql.mapper.AssetTypeMapper;
import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.graphql.type.input.CreateAssetTypeInput;
import digital.windmill.audra.service.AssetTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AssetTypeFacadeImpl implements AssetTypeFacade {

    private AssetTypeService assetTypeService;
    private AssetTypeMapper assetTypeMapper;

    @Override
    @Transactional(readOnly = true)
    public AssetType findAssetTypeByUuid(UUID uuid) {
        return assetTypeMapper.
                mapAssetTypeEntityToAssetType(assetTypeService.findAssetByUuid(uuid));
    }

    @Override
    public AssetType createAssetType(CreateAssetTypeInput createAssetTypeInput) {
        return assetTypeMapper
                .mapAssetTypeEntityToAssetType(
                        assetTypeService
                                .save(assetTypeMapper
                                        .mapAssetTypeInputToAssetTypeEntity(createAssetTypeInput)));

    }

    @Override
    public Page<AssetType> getAssetsType() {
        return assetTypeService
                .getAssetsType()
                .map(assetTypeMapper::mapAssetTypeEntityToAssetType);
    }
}