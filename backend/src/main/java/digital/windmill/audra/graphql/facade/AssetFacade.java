package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.input.AssetInput;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import digital.windmill.audra.graphql.mapper.AssetMapper;
import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.service.AssetService;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AssetFacade {

    private AssetService assetService;
    private AssetMapper assetMapper;

    @Transactional(readOnly = true)
    public Asset findAssetByUuid(UUID uuid) {
        return assetMapper.map(assetService.findByUuid(uuid));
    }

    @Transactional(readOnly = true)
    public Page<Asset> findAllAssets(AssetInput input) {
        return assetService.findAll(input)
                .map(assetMapper::map);
    }
}
