package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.AssetSpecification;
import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.dao.repository.AssetRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.enums.AssetSortEnum;
import digital.windmill.audra.graphql.type.input.AssetsInput;
import digital.windmill.audra.graphql.type.input.PageInput;
import digital.windmill.audra.graphql.utils.SortUtils;
import digital.windmill.audra.service.AssetService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AssetServiceImpl implements AssetService {

    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final List<AssetSortEnum> DEFAULT_SORT = List.of(AssetSortEnum.waybillDate_DESC);
    private AssetRepository assetRepository;

    @Override
    public AssetEntity findAssetByUuid(UUID uuid) {
        if (Objects.nonNull(uuid)) {
            return assetRepository.findByUuid(uuid).orElseThrow(
                    () -> new DataNotFoundException("Asset not found " + uuid));
        }
        return null;
    }

    @Override
    public Page<AssetEntity> findAll(AssetsInput input) {
        var itemsPerPage = Optional.ofNullable(input).map(AssetsInput::getPagination).map(PageInput::getItemsPerPage).orElse(DEFAULT_PAGE_SIZE);
        var pageNumber = Optional.ofNullable(input).map(AssetsInput::getPagination).map(PageInput::getPageNumber).orElse(0);
        var sortValues = Optional.ofNullable(input).map(AssetsInput::getSort).filter(e -> !e.isEmpty()).orElse(DEFAULT_SORT);
        var sort = sortValues.stream().map(e -> SortUtils.parse(e.name())).reduce(Sort::and).get();
        var pageable = PageRequest.of(pageNumber, itemsPerPage, sort);
        var spec = AssetSpecification.assets(input);
        return assetRepository.findAll(spec,pageable);
    }

    @Override
    public AssetEntity createOrUpdateAsset(AssetEntity entity) {

        return assetRepository.save(entity);
    }

}
