package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.PlaybookStepEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PlaybookStepSpecification {

    public static Specification<PlaybookStepEntity> byPlaybookStep(@NonNull List<PlaybookStepEntity> playbookStepEntities) {
        return (root, query, cb) -> {
            var predicates = new ArrayList<Predicate>();

            if (!playbookStepEntities.isEmpty()) {
                predicates.add(root.get("uuid").in(playbookStepEntities.stream().map(PlaybookStepEntity::getUuid).collect(toList())));
            }
            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }
}
