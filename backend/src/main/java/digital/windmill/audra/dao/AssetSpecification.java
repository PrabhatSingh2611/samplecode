package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.graphql.type.input.AssetWhereInput;
import digital.windmill.audra.graphql.type.input.AssetsInput;
import digital.windmill.audra.graphql.type.input.NodeInput;
import digital.windmill.audra.graphql.type.input.PageInput;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class AssetSpecification {

    private static final Integer DEFAULT_PAGE_SIZE = 10;

    public static Pair<Specification<AssetEntity>, PageRequest> assets(AssetsInput input) {
        var archived = Optional.ofNullable(input).map(AssetsInput::getWhere).map(AssetWhereInput::getArchived).orElse(null);
        var employee = Optional.ofNullable(input).map(AssetsInput::getWhere).map(AssetWhereInput::getEmployee).orElse(null);
        var type = Optional.ofNullable(input).map(AssetsInput::getWhere).map(AssetWhereInput::getType).orElse(null);

        var itemsPerPage = Optional.ofNullable(input).map(AssetsInput::getPage).map(PageInput::getItemsPerPage).orElse(DEFAULT_PAGE_SIZE);
        var pageNumber = Optional.ofNullable(input).map(AssetsInput::getPage).map(PageInput::getPageNumber).orElse(0);

        var spec = Specification.where(
                        byArchived(archived))
                .and(byEmployee(employee))
                .and(byType(type));

        var pageable = PageRequest.of(pageNumber, itemsPerPage);
        return Pair.of(spec, pageable);
    }

    public static Specification<AssetEntity> byArchived(Boolean archived) {
        return (root, query, builder) -> {
            if (archived == null) {
                return builder.conjunction();
            }
            if (archived) {
                return builder.isNotEmpty(root.get("archivedDate"));
            } else {
                return builder.isNull(root.get("archivedDate"));
            }
        };
    }

    public static Specification<AssetEntity> byEmployee(NodeInput employee) {
        return (root, query, builder) -> {
            if (employee == null || employee.getUuid() == null) {
                return builder.conjunction();
            }
            var employeeJoin = root.join("employee");
            return builder.equal(employeeJoin.get("uuid"), employee.getUuid());
        };
    }

    public static Specification<AssetEntity> byType(NodeInput type) {
        return (root, query, builder) -> {
            if (type == null || type.getUuid() == null) {
                return builder.conjunction();
            }
            var typeJoin = root.join("type");
            return builder.equal(typeJoin.get("uuid"), type.getUuid());
        };
    }
}