package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.mapper.AssetTypeMapper;
import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.graphql.type.input.AssetTypeInput;
import digital.windmill.audra.service.AssetTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AssetTypeFacade {

    private AssetTypeService assetTypeService;
    private AssetTypeMapper assetTypeMapper;

    @Transactional(readOnly = true)
    public AssetType findAssetTypeByUuid(UUID uuid) {
        return assetTypeMapper.map(assetTypeService.findByUuid(uuid));
    }

    public AssetType createAssetType(AssetTypeInput assetTypeInput){
        return assetTypeMapper.map(assetTypeService.createAssetType(assetTypeInput));
    }

    public List<AssetType> getAssetsType() {
        return assetTypeService
                .getAssetsType()
                .stream()
                .map(x -> assetTypeMapper.map(x))
                .collect(Collectors.toList());
    }
}