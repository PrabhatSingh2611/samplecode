
package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.mapper.AssetTypeCategoryMapper;
import digital.windmill.audra.graphql.type.assetTypeCategory.AssetTypeCategoriesInput;
import digital.windmill.audra.graphql.type.assetTypeCategory.AssetTypeCategory;
import digital.windmill.audra.graphql.type.assetTypeCategory.CreateAssetTypeCategoryInput;
import digital.windmill.audra.graphql.type.assetTypeCategory.PatchAssetTypeCategoryInput;
import digital.windmill.audra.service.impl.AssetTypeCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AssetTypeCategoryFacade {

    private AssetTypeCategoryService assetTypeCategoryService;
    private AssetTypeCategoryMapper assetTypeCategoryMapper;

    @Transactional(readOnly = true)
    public Page<AssetTypeCategory> getAssetTypeCategories(AssetTypeCategoriesInput input) {
        return assetTypeCategoryService.findAll(input).map(assetTypeCategoryMapper::map);
    }

    @Transactional
    public AssetTypeCategory createAssetTypeCategory(CreateAssetTypeCategoryInput createAssetTypeCategoryInput) {
        var category = assetTypeCategoryMapper.map(createAssetTypeCategoryInput);
        return assetTypeCategoryMapper.map(assetTypeCategoryService.save(category));
    }

    @Transactional
    public AssetTypeCategory patchAssetTypeCategory(PatchAssetTypeCategoryInput patchAssetTypeCategoryInput) {
        var category = assetTypeCategoryService.findAssetTypeCategoryByUuid(patchAssetTypeCategoryInput.getId());
        var patchedCategory = assetTypeCategoryMapper.map(patchAssetTypeCategoryInput, category);
        return assetTypeCategoryMapper.map(assetTypeCategoryService.save(patchedCategory));
    }
}