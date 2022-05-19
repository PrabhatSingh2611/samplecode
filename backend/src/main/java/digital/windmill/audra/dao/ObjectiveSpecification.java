package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.ObjectiveEntity;
import digital.windmill.audra.dao.entity.enums.ObjectiveStatus;
import digital.windmill.audra.graphql.type.input.EmployeeObjectivesWhereInput;
import digital.windmill.audra.graphql.type.input.NodeInput;
import digital.windmill.audra.graphql.type.input.ObjectivesInput;
import digital.windmill.audra.graphql.type.input.PageInput;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ObjectiveSpecification {

    public static Pair<Specification<ObjectiveEntity>, PageRequest> objectives(ObjectivesInput input, Integer DEFAULT_PAGE_SIZE) {
        var employee = Optional.ofNullable(input).map(ObjectivesInput::getWhere).map(EmployeeObjectivesWhereInput::getEmployee).orElse(null);
        var status = Optional.ofNullable(input).map(ObjectivesInput::getWhere).map(EmployeeObjectivesWhereInput::getStatus).orElse(null);

        var itemsPerPage = Optional.ofNullable(input).map(ObjectivesInput::getPagination).map(PageInput::getItemsPerPage).orElse(DEFAULT_PAGE_SIZE);
        var pageNumber = Optional.ofNullable(input).map(ObjectivesInput::getPagination).map(PageInput::getPageNumber).orElse(0);

        Specification<ObjectiveEntity> spec = new ObjectivePredicate(employee, status);

        var pageable = PageRequest.of(pageNumber, itemsPerPage);
        return Pair.of(spec, pageable);
    }

    @AllArgsConstructor
    @Data
    private static class ObjectivePredicate implements Specification<ObjectiveEntity> {
        private NodeInput employee;
        private List<ObjectiveStatus> statuses;

        @Override
        public Predicate toPredicate(Root<ObjectiveEntity> root,
                                     CriteriaQuery<?> query,
                                     CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(employee)) {
                var assignToJoin = root.join("employee");
                predicates.add(criteriaBuilder.equal(assignToJoin.get("uuid"), employee.getId()));
            }

            if (Objects.nonNull(statuses)) {
                predicates.add(root.get("status").in(statuses));
            }

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        }
    }
}