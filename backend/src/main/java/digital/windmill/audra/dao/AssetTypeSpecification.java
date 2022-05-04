package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.AssetTypeEntity;
import org.springframework.data.jpa.domain.Specification;

public class AssetTypeSpecification {


    public static Specification<AssetTypeEntity> allAssetTypes() {
        return (root, query, builder) -> {
            return builder.conjunction();
        };
    }
}
