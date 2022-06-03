package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.AssetTypeCategoryEntity;
import digital.windmill.audra.graphql.type.assetTypeCategory.AssetTypeCategoriesWhereInput;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class AssetTypeCategorySpecification {

    public static Specification<AssetTypeCategoryEntity> allAssetTypeCategories(AssetTypeCategoriesWhereInput where) {
        var name = Optional.ofNullable(where).map(AssetTypeCategoriesWhereInput::getName).orElse(null);
        return Specification.where(byName(name));
    }

    public static Specification<AssetTypeCategoryEntity> byName(String name) {
        return (root, query, builder) -> {
            if (name == null || name.isBlank()) {
                return builder.conjunction();
            }
            var pattern = "%" + name.toLowerCase() + "%";
            return builder.like(builder.lower(root.get("name")), pattern);
        };
    }
}
