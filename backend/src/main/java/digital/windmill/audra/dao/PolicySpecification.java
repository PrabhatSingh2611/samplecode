package digital.windmill.audra.dao;


import digital.windmill.audra.dao.entity.PolicyEntity;
import digital.windmill.audra.dao.entity.enums.PolicySort;
import digital.windmill.audra.graphql.type.input.PoliciesWhereInput;
import lombok.NonNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static digital.windmill.audra.dao.SpecificationUtils.direction;

public class PolicySpecification {
    public static Specification<PolicyEntity> byPoliciesInput(PoliciesWhereInput input, List<PolicySort> sort) {

        Specification<PolicyEntity> byQuery = Optional.ofNullable(input)
                .map(PoliciesWhereInput::getQuery)
                .map(PolicySpecification::byQuery)
                .orElse(null);

        Specification<PolicyEntity> addCustomSorting = sortedBy(sort);

        return Stream.of(byQuery, addCustomSorting)
                .filter(Objects::nonNull)
                .reduce(PolicySpecification.any(), Specification::and);

    }

    private static Specification<PolicyEntity> any() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

    private static Specification<PolicyEntity> sortedBy(List<PolicySort> sort) {
        return (root, query, criteriaBuilder) -> {
            List<Order> orders = new ArrayList<>();
            for (PolicySort sortItem : sort) {
                var order = prepareOrder(root, criteriaBuilder, sortItem);
                if (order != null) {
                    orders.add(order);
                }
            }
            query.orderBy(orders);
            return null;
        };
    }

    private static Specification<PolicyEntity> byQuery(@NonNull String queryString) {
        return (root, query, cb) -> cb.like(root.get("title"), "%" + queryString + "%");
    }

    private static Order prepareOrder(Root<PolicyEntity> root, CriteriaBuilder criteriaBuilder, PolicySort sortItem) {
        if (sortItem == null) {
            return null;
        }
        return switch (sortItem) {
            case publicationDate_DESC -> direction(criteriaBuilder, root.get("publicationDate"), Sort.Direction.DESC);
            case publicationDate_ASC -> direction(criteriaBuilder, root.get("publicationDate"), Sort.Direction.ASC);
        };
    }
}
