package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.AssetTypeCategorySpecification;
import digital.windmill.audra.dao.entity.AssetTypeCategoryEntity;
import digital.windmill.audra.dao.repository.AssetTypeCategoryRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.assetType.AssetTypeSort;
import digital.windmill.audra.graphql.type.assetTypeCategory.AssetTypeCategoriesInput;
import digital.windmill.audra.graphql.type.input.PageInput;
import digital.windmill.audra.graphql.utils.SortUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AssetTypeCategoryService {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final List<AssetTypeSort> DEFAULT_SORT = List.of(AssetTypeSort.createdAt_DESC);

    private AssetTypeCategoryRepository assetTypeCategoryRepository;

    public AssetTypeCategoryEntity findAssetTypeCategoryByUuid(UUID uuid) {
        if (uuid == null) return null;

        return assetTypeCategoryRepository.findByUuid(uuid).orElseThrow(() -> new DataNotFoundException("Category not found " + uuid));
    }

    public Page<AssetTypeCategoryEntity> findAll(AssetTypeCategoriesInput input) {
        var itemsPerPage = Optional.ofNullable(input).map(AssetTypeCategoriesInput::getPagination).map(PageInput::getItemsPerPage).orElse(DEFAULT_PAGE_SIZE);
        var pageNumber = Optional.ofNullable(input).map(AssetTypeCategoriesInput::getPagination).map(PageInput::getPageNumber).orElse(DEFAULT_PAGE_NUMBER);
        var sortValues = Optional.ofNullable(input).map(AssetTypeCategoriesInput::getSort).filter(e -> !e.isEmpty()).orElse(DEFAULT_SORT);
        var sort = sortValues.stream().map(e -> SortUtils.parse(e.name())).reduce(Sort::and).get();

        var pageable = PageRequest.of(pageNumber, itemsPerPage, sort);
        var spec = AssetTypeCategorySpecification.allAssetTypeCategories(input.getWhere());

        return assetTypeCategoryRepository.findAll(spec, pageable);
    }

    public AssetTypeCategoryEntity save(AssetTypeCategoryEntity assetTypeCategory) {
        return assetTypeCategoryRepository.save(assetTypeCategory);
    }
}
