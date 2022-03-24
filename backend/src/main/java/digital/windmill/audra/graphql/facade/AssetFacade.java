package digital.windmill.audra.graphql.facade;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import digital.windmill.audra.graphql.mapper.AssetMapper;
import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.service.AssetService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AssetFacade {

    private AssetService assetService;
    private AssetMapper assetMapper;

    @Transactional(readOnly = true)
    public Asset findAssetById(Long id) {
        return assetMapper.map(assetService.findById(id));
    }

    @Transactional(readOnly = true)
    public Page<Asset> findAllAssets() {
        return assetService.findAll()
                .map(assetMapper::map);
    }
}
