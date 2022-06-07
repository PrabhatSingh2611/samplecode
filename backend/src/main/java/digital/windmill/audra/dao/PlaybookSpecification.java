package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.PlaybookEntity;
import digital.windmill.audra.graphql.type.enums.PlaybookSort;
import digital.windmill.audra.graphql.type.input.PlaybooksWhereInput;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static digital.windmill.audra.dao.SpecificationUtils.direction;

public class PlaybookSpecification {

    public static Specification<PlaybookEntity> byPlaybookInput(@NonNull PlaybooksWhereInput input, List<PlaybookSort> sort) {

        Specification<PlaybookEntity> byName = Optional.ofNullable(input)
                .map(PlaybooksWhereInput::getName)
                .map(PlaybookSpecification::byName)
                .orElse(null);

        Specification<PlaybookEntity> addCustomSorting = sortedBy(sort);

        return Stream.of(byName, addCustomSorting)
                .filter(Objects::nonNull)
                .reduce(PlaybookSpecification.any(), Specification::and);

    }

    public static Specification<PlaybookEntity> byName(@lombok.NonNull String name) {
        return (root, query, cb) -> {

            var predicates = new ArrayList<Predicate>();

            if (StringUtils.isNotBlank(name)) {
                predicates.add(cb.equal(root.get("name"), name));
            }
            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }

    private static Specification<PlaybookEntity> sortedBy(List<PlaybookSort> sort) {
        return (root, query, criteriaBuilder) -> {
            if (CollectionUtils.isNotEmpty(sort)) {
                List<Order> orders = new ArrayList<>();
                for (PlaybookSort sortItem : sort) {
                    var order = prepareOrder(root, criteriaBuilder, sortItem);
                    if (order != null) {
                        orders.add(order);
                    }
                }
                query.orderBy(orders);
            }
            return null;
        };
    }

    private static Order prepareOrder(Root<PlaybookEntity> root, CriteriaBuilder criteriaBuilder, PlaybookSort sortItem) {
        if (sortItem == null) {
            return null;
        }
        return switch (sortItem) {
            case updatedAt_ASC -> direction(criteriaBuilder, root.get("updatedAt"), Sort.Direction.ASC);
            case updatedAt_DESC -> direction(criteriaBuilder, root.get("updatedAt"), Sort.Direction.DESC);
        };
    }

    public static Specification<PlaybookEntity> any() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }
}
