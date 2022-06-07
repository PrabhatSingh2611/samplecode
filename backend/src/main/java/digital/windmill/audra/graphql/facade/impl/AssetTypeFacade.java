package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.mapper.AssetTypeMapper;
import digital.windmill.audra.graphql.type.assetType.AssetType;
import digital.windmill.audra.graphql.type.assetType.AssetTypesInput;
import digital.windmill.audra.graphql.type.assetType.CreateAssetTypeInput;
import digital.windmill.audra.graphql.type.assetType.UpdateAssetTypeInput;
import digital.windmill.audra.service.impl.AssetTypeCategoryService;
import digital.windmill.audra.service.impl.AssetTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AssetTypeFacade {

    private AssetTypeService assetTypeService;
    private AssetTypeCategoryService assetTypeCategoryService;
    private AssetTypeMapper assetTypeMapper;

    @Transactional(readOnly = true)
    public AssetType findAssetTypeByUuid(UUID uuid) {
        return assetTypeMapper.map(assetTypeService.findAssetByUuid(uuid));
    }

    @Transactional(readOnly = true)
    public List<AssetType> findAssetTypesByCategoryUuid(UUID uuid) {
        return assetTypeCategoryService.findAssetTypeCategoryByUuid(uuid).getTypes()
                .stream().map(assetTypeMapper::map).toList();
    }

    @Transactional(readOnly = true)
    public Page<AssetType> getAssetTypes(AssetTypesInput input) {
        return assetTypeService.getAssetTypes(input)
                .map(assetTypeMapper::map);
    }

    @Transactional
    public AssetType createAssetType(CreateAssetTypeInput createAssetTypeInput) {
        var assetType = assetTypeMapper.map(createAssetTypeInput);
        var category = createAssetTypeInput.getCategory() == null ? null :
                assetTypeCategoryService.findAssetTypeCategoryByUuid(createAssetTypeInput.getCategory().getId());
        assetType.setCategory(category);
        return assetTypeMapper.map(assetTypeService.save(assetType));
    }

    @Transactional
    public AssetType updateAssetType(UpdateAssetTypeInput updateAssetTypeInput) {
        var assetType = assetTypeService.findAssetByUuid(updateAssetTypeInput.getId());
        var category = updateAssetTypeInput.getCategory() == null ? null :
                assetTypeCategoryService.findAssetTypeCategoryByUuid(updateAssetTypeInput.getCategory().getId());
        assetType.setCategory(category);
        var updatedAssetType = assetTypeMapper.map(updateAssetTypeInput, assetType);
        return assetTypeMapper.map(assetTypeService.save(updatedAssetType));
    }

}