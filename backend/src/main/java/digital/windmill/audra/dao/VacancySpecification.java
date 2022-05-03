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
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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
                var assignToJoin = root.join("assignTo");
                predicates.add(criteriaBuilder.equal(assignToJoin.get("uuid"), assignTo.getUuid()));
            }

            if (Objects.nonNull(positions)) {
                var position = root.join("position");
                predicates.add(position.get("uuid").in(positions.stream().map(NodeInput::getUuid).collect(toList())));
            }

             if (Objects.nonNull(statuses)) {
                predicates.add(root.get("status").in(statuses));
            }

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        }
    }
}