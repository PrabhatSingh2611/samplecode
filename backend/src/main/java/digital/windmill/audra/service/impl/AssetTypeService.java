package digital.windmill.audra.service.impl;


import digital.windmill.audra.dao.AssetTypeSpecification;
import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.dao.repository.AssetTypeRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.assetType.AssetTypeSort;
import digital.windmill.audra.graphql.type.assetType.AssetTypesInput;
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
public class AssetTypeService {

    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final List<AssetTypeSort> DEFAULT_SORT = List.of(AssetTypeSort.createdAt_DESC);


    private AssetTypeRepository assetTypeRepository;

    public AssetTypeEntity findAssetByUuid(UUID uuid) {
        if (Optional.ofNullable(uuid).isPresent()) {
            return assetTypeRepository.findByUuid(uuid).orElseThrow(
                    () -> new DataNotFoundException("Asset Type not found for : " + uuid)
            );
        } else
            return null;
    }

    public Page<AssetTypeEntity> getAssetTypes(AssetTypesInput input) {
        var itemsPerPage = Optional.ofNullable(input.getPagination()).map(PageInput::getItemsPerPage).orElse(DEFAULT_PAGE_SIZE);
        var pageNumber = Optional.ofNullable(input.getPagination()).map(PageInput::getPageNumber).orElse(DEFAULT_PAGE_NUMBER);
        var sortValues = Optional.ofNullable(input.getSort()).filter(e -> !e.isEmpty()).orElse(DEFAULT_SORT);
        var sort = sortValues.stream().map(e -> SortUtils.parse(e.name())).reduce(Sort::and).get();

        var spec = AssetTypeSpecification.allAssetTypes(input.getWhere());
        var page = PageRequest.of(pageNumber, itemsPerPage, sort);
        return assetTypeRepository.findAll(spec, page);
    }

    public AssetTypeEntity save(AssetTypeEntity assetTypeEntity) {
        return assetTypeRepository.save(assetTypeEntity);
    }

}
