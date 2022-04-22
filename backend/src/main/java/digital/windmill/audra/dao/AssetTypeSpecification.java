package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.AssetTypeEntity;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

public class AssetTypeSpecification {
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final Integer DEFAULT_PAGE_NUMBER=1;
    public static Pair<Specification<AssetTypeEntity>, PageRequest> assetTypes() {

        var itemsPerPage = DEFAULT_PAGE_SIZE;
        var pageNumber = DEFAULT_PAGE_NUMBER;
        var spec = Specification.where(
                        byAssetType());

        var pageable = PageRequest.of(pageNumber, itemsPerPage);
        return Pair.of(spec, pageable);
    }

    public static Specification<AssetTypeEntity> byAssetType() {
        return (root, query, builder) -> {
           return builder.conjunction();
        };
    }
}
