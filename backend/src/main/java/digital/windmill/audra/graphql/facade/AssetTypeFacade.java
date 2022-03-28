package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.mapper.AssetTypeMapper;
import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.service.AssetTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AssetTypeFacade {

    private AssetTypeService assetTypeService;
    private AssetTypeMapper assetTypeMapper;

    @Transactional(readOnly = true)
    public AssetType findAssetTypeByUuid(UUID uuid) {
        return assetTypeMapper.map(assetTypeService.findByUuid(uuid));
    }
}
