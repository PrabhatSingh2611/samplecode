package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.QuestionEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class QuestionSpecification {

    public static Specification<QuestionEntity> byQuestions(@NonNull List<QuestionEntity> questions) {
        return (root, query, cb) -> {
            var predicates = new ArrayList<Predicate>();

            if (!questions.isEmpty()) {
                predicates.add(root.get("uuid").in(questions.stream().map(QuestionEntity::getUuid).collect(toList())));
            }
            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }
}
