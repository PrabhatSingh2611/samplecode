package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.SurveyEntity;
import digital.windmill.audra.graphql.type.input.SurveyWhereInput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Objects;

public class SurveySpecification {
    public static Specification<SurveyEntity> bySurveysInput(@NonNull SurveyWhereInput input) {
        return (root, query, cb) -> {
            var predicates = new ArrayList<Predicate>();

            if (Objects.nonNull(input.getTitle())) {
                predicates.add(cb.equal(root.get("title"), input.getTitle()));
            }
            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }
}
