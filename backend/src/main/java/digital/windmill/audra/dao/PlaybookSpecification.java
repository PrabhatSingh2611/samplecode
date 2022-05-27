package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.PlaybookEntity;
import digital.windmill.audra.graphql.type.input.PlaybooksWhereInput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Objects;

public class PlaybookSpecification {

    public static Specification<PlaybookEntity> byPlaybookInput(@NonNull PlaybooksWhereInput input) {
        return (root, query, cb) -> {

            var predicates = new ArrayList<Predicate>();

            if (Objects.nonNull(input) && Objects.nonNull(input.getTitle())) {
                predicates.add(cb.equal(root.get("title"), input.getTitle()));
            }
            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }
}
