package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.graphql.type.assetType.AssetTypesWhereInput;
import digital.windmill.audra.graphql.type.input.NodeInput;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class AssetTypeSpecification {

    public static Specification<AssetTypeEntity> allAssetTypes(AssetTypesWhereInput where) {
        var name = Optional.ofNullable(where).map(AssetTypesWhereInput::getName).orElse(null);
        var category = Optional.ofNullable(where).map(AssetTypesWhereInput::getCategory).orElse(null);
        return Specification.where(
                byName(name).and(byCategory(category)));
    }

    public static Specification<AssetTypeEntity> byName(String name) {
        return (root, query, builder) -> {
            if (name == null || name.isBlank()) {
                return builder.conjunction();
            }
            var pattern = "%" + name.toLowerCase() + "%";
            return builder.like(builder.lower(root.get("name")), pattern);
        };
    }

    public static Specification<AssetTypeEntity> byCategory(NodeInput categoryInput) {
        return (root, query, builder) -> {
            if (categoryInput == null) {
                return builder.conjunction();
            }
            var country = root.join("category");
            return builder.equal(country.get("uuid"),  categoryInput.getId());
        };
    }
}
