package digital.windmill.audra.dao;


import digital.windmill.audra.dao.entity.VacancyEntity;
import digital.windmill.audra.dao.entity.enums.VacancyStatus;
import digital.windmill.audra.graphql.type.input.NodeInput;
import digital.windmill.audra.graphql.type.input.PageInput;
import digital.windmill.audra.graphql.type.input.VacanciesInput;
import digital.windmill.audra.graphql.type.input.VacancyWhereInput;
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
import java.util.UUID;

public class VacancySpecification {
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    public static Pair<Specification<VacancyEntity>, PageRequest> vacancies(VacanciesInput input) {
        var assignTo = Optional.ofNullable(input).map(VacanciesInput::getWhere).map(VacancyWhereInput::getAssignTo).orElse(null);
        var position = Optional.ofNullable(input).map(VacanciesInput::getWhere).map(VacancyWhereInput::getPosition).orElse(null);
        var status = Optional.ofNullable(input).map(VacanciesInput::getWhere).map(VacancyWhereInput::getStatus).orElse(null);

        var itemsPerPage = Optional.ofNullable(input).map(VacanciesInput::getPagination).map(PageInput::getItemsPerPage).orElse(DEFAULT_PAGE_SIZE);
        var pageNumber = Optional.ofNullable(input).map(VacanciesInput::getPagination).map(PageInput::getPageNumber).orElse(0);

        Specification<VacancyEntity> spec = new VacancyPredicate(assignTo, position, status);

        var pageable = PageRequest.of(pageNumber, itemsPerPage);
        return Pair.of(spec, pageable);
    }

    @AllArgsConstructor
    @Data
    private static class VacancyPredicate implements Specification<VacancyEntity> {
        private NodeInput assignTo;
        private List<NodeInput> positions;
        private List<VacancyStatus> statuses;

        @Override
        public Predicate toPredicate(Root<VacancyEntity> root,
                                     CriteriaQuery<?> query,
                                     CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(assignTo)) {
                var assignToJoin = root.join("employee");
                predicates.add(criteriaBuilder.equal(assignToJoin.get("uuid"), assignTo.getUuid()));
            }

            if (Objects.nonNull(positions)) {
                var position = root.join("employee_position");
                CriteriaBuilder.In<UUID> inClause = criteriaBuilder.in(position.get("uuid"));
                positions.forEach(p -> inClause.value(p.getUuid()));
                predicates.add(criteriaBuilder.in(position.get("uuid")).value(inClause));
            }

            if (Objects.nonNull(statuses)) {
                CriteriaBuilder.In<VacancyStatus> inClause = criteriaBuilder.in(root.get("status"));
                statuses.forEach(inClause::value);
                predicates.add(criteriaBuilder.in(root.get("status")).value(inClause));
            }

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        }
    }
}