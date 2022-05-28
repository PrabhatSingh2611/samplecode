package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.type.enums.EmployeeSort;
import digital.windmill.audra.graphql.type.input.*;
import lombok.NonNull;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.stream.Stream;

import static digital.windmill.audra.dao.SpecificationUtils.direction;

public class EmployeeSpecification {
  public static Specification<EmployeeEntity> employees(EmployeeWhereInput input, List<EmployeeSort> sort) {

        Specification<EmployeeEntity> byQuery = Optional.ofNullable(input)
                .map(EmployeeWhereInput::getQuery)
                .map(EmployeeSpecification::bySearchText)
                .orElse(null);

        Specification<EmployeeEntity> byLocation = Optional.ofNullable(input)
                .map(EmployeeWhereInput::getLocation)
                .map(NodeInput::getId)
                .map(EmployeeSpecification::byLocation)
                .orElse(null);

        Specification<EmployeeEntity> addCustomSorting = sortedBy(sort);
        return Stream.of(byQuery, byLocation, addCustomSorting)
                .filter(Objects::nonNull)
                .reduce(EmployeeSpecification.any(), Specification::and);
    }
    public static Specification<EmployeeEntity> any() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

    public static Specification<EmployeeEntity> byLocation(@NonNull UUID locationUuid) {
        return (root, query, criteriaBuilder) -> {
            Join<EmployeeEntity, LocationEntity> locationJoin = root.join("location");
            return criteriaBuilder.equal(locationJoin.get("uuid"), locationUuid);
        };
    }

    private static Specification<EmployeeEntity> sortedBy(List<EmployeeSort> sort) {
         return (root, query, criteriaBuilder) -> {
            if (CollectionUtils.isNotEmpty(sort)) {
                List<Order> orders = new ArrayList<>();
                for (EmployeeSort sortItem : sort) {
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


    private static Order prepareOrder(Root<EmployeeEntity> root, CriteriaBuilder criteriaBuilder, EmployeeSort sortItem) {
        if (sortItem == null) {
            return null;
        }
        return switch (sortItem) {
            case firstName_ASC -> direction(criteriaBuilder, root.get("firstName"), Sort.Direction.ASC);
            case firstName_DESC -> direction(criteriaBuilder, root.get("firstName"), Sort.Direction.DESC);
        };
    }

    public static Specification<EmployeeEntity> bySearchText(String searchText) {
        return (root, query, builder) -> {
            if (!StringUtils.isNotBlank(searchText)) {
                return builder.conjunction();
            }
            var pattern = "%" + searchText.toLowerCase().trim() + "%";
            return builder.or(
                    builder.like(builder.lower(root.get("firstName")), pattern),
                    builder.like(builder.lower(root.get("lastName")), pattern),
                    builder.like(builder.lower(root.get("email")), pattern)
            );
        };
    }
}
