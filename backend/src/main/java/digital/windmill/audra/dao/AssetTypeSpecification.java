package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.AssetTypeEntity;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

public class AssetTypeSpecification {


    public static Specification<AssetTypeEntity> byAssetType() {
        return (root, query, builder) -> {
           return builder.conjunction();
        };
    }
}
