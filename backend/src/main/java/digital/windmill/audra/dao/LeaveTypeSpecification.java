package digital.windmill.audra.dao;

import static digital.windmill.audra.dao.SpecificationUtils.direction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import digital.windmill.audra.dao.entity.LeaveTypeEntity;
import digital.windmill.audra.dao.entity.enums.LeaveTypesSortEnum;
import digital.windmill.audra.graphql.type.input.LeaveTypesWhereInput;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LeaveTypeSpecification {

    public static Specification<LeaveTypeEntity> leaveTypes(LeaveTypesWhereInput input, 
            List<LeaveTypesSortEnum> sort) {
        return sortedBy(sort);
    }

    public static Specification<LeaveTypeEntity> sortedBy(List<LeaveTypesSortEnum> sort) {
        return (root, query, criteriaBuilder) -> {
            List<Order> orders = new ArrayList<>();
            for (LeaveTypesSortEnum sortItem: sort) {
                var order = prepareOrder(root, criteriaBuilder, sortItem);
                if (order != null) {
                    orders.add(order);
                }
            }
            query.orderBy(orders);
            return null;
        };
    }

    private static Order prepareOrder(Root<LeaveTypeEntity> root, CriteriaBuilder cb, LeaveTypesSortEnum order) {
        if (order == null) {
            return null;
        }
        return switch (order) {
            case name_ASC -> direction( cb, root.get("name"), Sort.Direction.ASC);
            case name_DESC -> direction( cb, root.get("name"), Sort.Direction.DESC);
        };
    }
}