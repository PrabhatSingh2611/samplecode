package digital.windmill.audra.service;


import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.dao.repository.AssetTypeRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.input.AssetTypeInput;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AssetTypeService {

    private AssetTypeRepository assetTypeRepository;

    public AssetTypeEntity findByUuid(UUID uuid) {
        return assetTypeRepository.findByUuid(uuid).orElseThrow(
                () -> new DataNotFoundException("Asset type not found.")
        );
    }

    public List<AssetTypeEntity> getAssetsType(){
        return assetTypeRepository.findAll();
    }

    public AssetTypeEntity createAssetType(AssetTypeInput input) {
        return assetTypeRepository.save(AssetTypeEntity
                .builder()
                .uuid(UUID.randomUUID())
                .build());
    }

}
