package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.graphql.type.enums.AssetSortEnum;
import digital.windmill.audra.graphql.type.input.AssetWhereInput;
import digital.windmill.audra.graphql.type.input.AssetsInput;
import digital.windmill.audra.graphql.type.input.NodesInput;
import digital.windmill.audra.graphql.type.input.PageInput;
import digital.windmill.audra.graphql.utils.SortUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

public class AssetSpecification {

    public static Specification<AssetEntity> assets(AssetsInput input) {
        var query = Optional.ofNullable(input).map(AssetsInput::getWhere).map(AssetWhereInput::getQuery).orElse(null);
        var archived = Optional.ofNullable(input).map(AssetsInput::getWhere).map(AssetWhereInput::getArchived).orElse(null);
        var assignee = Optional.ofNullable(input).map(AssetsInput::getWhere).map(AssetWhereInput::getAssignee).orElse(null);
        var location = Optional.ofNullable(input).map(AssetsInput::getWhere).map(AssetWhereInput::getLocation).orElse(null);
        var type = Optional.ofNullable(input).map(AssetsInput::getWhere).map(AssetWhereInput::getType).orElse(null);

        var spec = Specification
                .where(byArchived(archived))
                .and(bySearchText(query))
                .and(byAssignee(assignee))
                .and(byLocation(location))
                .and(byType(type));
        return spec;
    }

    public static Specification<AssetEntity> byArchived(Boolean archived) {
        return (root, query, builder) -> {
            if (archived == null) {
                return builder.conjunction();
            }
            if (archived) {
                return builder.isNotNull(root.get("archivedDate"));
            } else {
                return builder.isNull(root.get("archivedDate"));
            }
        };
    }

    public static Specification<AssetEntity> byAssignee(NodesInput assignee) {
        return (root, query, builder) -> {
            if (assignee == null || CollectionUtils.isEmpty(assignee.getIds())) {
                return builder.conjunction();
            }
            var assigneeJoin = root.join("assignee");
            return builder.in(assigneeJoin.get("uuid")).value(assignee.getIds());
        };
    }

    public static Specification<AssetEntity> byType(NodesInput type) {
        return (root, query, builder) -> {
            if (type == null || CollectionUtils.isEmpty(type.getIds())) {
                return builder.conjunction();
            }
            var typeJoin = root.join("type");
            return builder.in(typeJoin.get("uuid")).value(type.getIds());
        };
    }

    public static Specification<AssetEntity> byLocation(NodesInput location) {
        return (root, query, builder) -> {
            if (location == null || CollectionUtils.isEmpty(location.getIds())) {
                return builder.conjunction();
            }
            var locationJoin = root.join("location");
            return builder.in(locationJoin.get("uuid")).value(location.getIds());
        };
    }

    public static Specification<AssetEntity> bySearchText(String searchText) {
        return (root, query, builder) -> {
            if (searchText == null || searchText.isBlank()) {
                return builder.conjunction();
            }
            var pattern = "%" + searchText.toLowerCase().trim() + "%";
            return builder.or(
                    builder.like(builder.lower(root.get("title")), pattern),
                    builder.like(builder.lower(root.get("serialNumber")), pattern),
                    builder.like(builder.lower(root.get("tagNumber")), pattern)
            );
        };
    }
}