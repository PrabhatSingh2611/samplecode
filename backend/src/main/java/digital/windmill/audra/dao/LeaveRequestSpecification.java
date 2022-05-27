package digital.windmill.audra.dao;


import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.LeaveRequestEntity;
import digital.windmill.audra.graphql.type.input.LeaveRequestWhereInput;
import digital.windmill.audra.graphql.type.input.LeaveRequestsSortEnum;
import digital.windmill.audra.graphql.type.input.NodeInput;
import lombok.NonNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static digital.windmill.audra.dao.SpecificationUtils.direction;

public class LeaveRequestSpecification {

    public static Specification<LeaveRequestEntity> byLeaveRequestsInput(LeaveRequestWhereInput input, List<LeaveRequestsSortEnum> sort) {

        Specification<LeaveRequestEntity> byEmployee = Optional.ofNullable(input)
            .map(LeaveRequestWhereInput::getEmployee)
            .map(NodeInput::getId)
            .map(LeaveRequestSpecification::byEmployee)
            .orElse(null);

        Specification<LeaveRequestEntity> byApprover = Optional.ofNullable(input)
                .map(LeaveRequestWhereInput::getApprover)
                .map(NodeInput::getId)
                .map(LeaveRequestSpecification::byReportingManager)
                .orElse(null);

        Specification<LeaveRequestEntity> afterStartDate = Optional.ofNullable(input)
                .map(LeaveRequestWhereInput::getStartDate)
                .map(ZonedDateTime::toInstant)
                .map(LeaveRequestSpecification::afterStartDate)
                .orElse(null);

        Specification<LeaveRequestEntity> beforeEndDate = Optional.ofNullable(input)
                .map(LeaveRequestWhereInput::getEndDate)
                .map(ZonedDateTime::toInstant)
                .map(LeaveRequestSpecification::afterStartDate)
                .orElse(null);

        Specification<LeaveRequestEntity> addCustomSorting = sortedBy(sort);
        return Stream.of(byEmployee, byApprover, afterStartDate, beforeEndDate, addCustomSorting)
                .filter(Objects::nonNull)
                .reduce(LeaveRequestSpecification.any(), (s1, s2) -> s1.and(s2));
    }

    public static Specification<LeaveRequestEntity> any() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

    public static Specification<LeaveRequestEntity> byEmployee(@NonNull UUID employeeUuid) {
        return (root, query, criteriaBuilder) -> {
            Join<LeaveRequestEntity, EmployeeEntity> employeeJoin = root.join("employee");
            return criteriaBuilder.equal(employeeJoin.get("uuid"), employeeUuid);
        };
    }

    public static Specification<LeaveRequestEntity> byReportingManager(@NonNull UUID managerUuid) {
        return (root, query, criteriaBuilder) -> {
            Join<LeaveRequestEntity, EmployeeEntity> employeeJoin = root.join("employee");
            Join<EmployeeEntity, EmployeeEntity> reportingManager = employeeJoin.join("reportingManager");
            return criteriaBuilder.equal(reportingManager.get("uuid"), managerUuid);
        };
    }

    public static Specification<LeaveRequestEntity> afterStartDate(Instant date) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), date);
    }

    public static Specification<LeaveRequestEntity> beforeEndDate(Instant date) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), date);
    }

    public static Specification<LeaveRequestEntity> sortedBy(List<LeaveRequestsSortEnum> sort) {
        return (root, query, criteriaBuilder) -> {
            List<Order> orders = new ArrayList<>();
            for (LeaveRequestsSortEnum sortItem: sort) {
                var order = prepareOrder(root, criteriaBuilder, sortItem);
                if (order != null) {
                    orders.add(order);
                }
            }
            query.orderBy(orders);
            return null;
        };
    }

    private static Order prepareOrder(Root<LeaveRequestEntity> root, CriteriaBuilder cb, LeaveRequestsSortEnum order) {
        if (order == null) {
            return null;
        }
        return switch (order) {
            case pending_ASC -> direction( cb, root.get("orderByPending"), Sort.Direction.ASC);
            case pending_DESC -> direction( cb, root.get("orderByPending"), Sort.Direction.DESC);
            case startDate_ASC -> direction( cb, root.get("startDate"), Sort.Direction.ASC);
            case startDate_DESC -> direction( cb, root.get("startDate"), Sort.Direction.DESC);
        };
    }

}