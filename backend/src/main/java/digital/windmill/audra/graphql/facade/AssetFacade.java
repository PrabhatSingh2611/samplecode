package digital.windmill.audra.graphql.facade;

import org.springframework.stereotype.Service;

import digital.windmill.audra.graphql.mapper.AssetMapper;
import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.service.AssetService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AssetFacade {

    private AssetService assetService;
    private AssetMapper assetMapper;

    public Asset findAssetById(Long id) {
        return assetMapper.map(assetService.findById(id));
    }
}
