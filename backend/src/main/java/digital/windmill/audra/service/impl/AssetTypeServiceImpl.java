package digital.windmill.audra.service.impl;


import digital.windmill.audra.dao.AssetTypeSpecification;
import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.dao.repository.AssetTypeRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.service.AssetTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AssetTypeServiceImpl implements AssetTypeService {

    private AssetTypeRepository assetTypeRepository;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final Integer DEFAULT_PAGE_NUMBER = 0;

    @Override
    public AssetTypeEntity findAssetByUuid(UUID uuid) {
        if (Objects.nonNull(uuid)) {
            return assetTypeRepository.findByUuid(uuid).orElseThrow(
                    () -> new DataNotFoundException("Asset Type not found " + uuid));
        }
        return null;
    }

    @Override
    public Page<AssetTypeEntity> getAssetsType() {
        Specification<AssetTypeEntity> specification = AssetTypeSpecification.allAssetTypes();
        PageRequest pagination = PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
        return assetTypeRepository.findAll(specification, pagination);
    }

    @Override
    public AssetTypeEntity createAssetType(AssetTypeEntity assetTypeEntity) {
        return assetTypeRepository.save(assetTypeEntity);
    }

}
