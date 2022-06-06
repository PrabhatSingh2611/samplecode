package digital.windmill.audra.feed.dao.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import digital.windmill.audra.dao.SpecificationUtils;
import digital.windmill.audra.feed.dao.entity.FeedItemEntity;
import digital.windmill.audra.feed.graphql.type.FeedSort;

public class FeedItemSpecification {

    public static Specification<FeedItemEntity> sorted(List<FeedSort> sort) {

        Specification<FeedItemEntity> addCustomSorting = sortedBy(sort);
        return Stream.of(addCustomSorting).filter(Objects::nonNull).reduce(FeedItemSpecification.any(),
                (s1, s2) -> s1.and(s2));
    }

    public static Specification<FeedItemEntity> any() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

    public static Specification<FeedItemEntity> sortedBy(List<FeedSort> sort) {
        return (root, query, criteriaBuilder) -> {
            if (CollectionUtils.isNotEmpty(sort)) {
                List<Order> orders = new ArrayList<>();
                for (FeedSort sortItem : sort) {
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

    private static Order prepareOrder(Root<FeedItemEntity> root, CriteriaBuilder cb, FeedSort order) {
        if (order == null) {
            return null;
        }
        return switch (order) {
        case createdAt_DESC -> SpecificationUtils.direction(cb, root.get("createdAt"), Sort.Direction.DESC);
        case createdAt_ASC -> SpecificationUtils.direction(cb, root.get("createdAt"), Sort.Direction.ASC);
        };
    }

}