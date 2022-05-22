package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.CandidateEntity;
import digital.windmill.audra.dao.entity.enums.CandidateStatus;
import digital.windmill.audra.graphql.type.input.CandidatesInput;
import digital.windmill.audra.graphql.type.input.NodeInput;
import digital.windmill.audra.graphql.type.input.VacancyCandidateWhereInput;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class CandidateSpecification {

    public static Specification<CandidateEntity> byCandidatesInput(@NonNull CandidatesInput input) {
        return ((root, query, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();
            if (input.getWhere() == null) {
                return criteriaBuilder.conjunction();
            }
            if (input.getWhere().getVacancy() != null) {
                predicates.add(criteriaBuilder.equal(root.get("uuid"), input.getWhere().getVacancy().getId()));
            }
            if (input.getWhere().getStatus() != null) {
                predicates.add(root.get("status").in(input.getWhere().getStatus()));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        });
    }
}