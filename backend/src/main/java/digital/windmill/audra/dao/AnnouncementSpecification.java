package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.AnnouncementEntity;
import digital.windmill.audra.graphql.type.input.AnnouncementsInput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class AnnouncementSpecification {
    public static Specification<AnnouncementEntity> byAnnouncementsInput(@NonNull AnnouncementsInput input) {
        return (root, query, cb) -> {
            var predicates = new ArrayList<Predicate>();

            if (input.getFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), input.getFrom().toInstant()));
            }

            if (input.getTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), input.getTo().toInstant()));
            }

            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }
}
