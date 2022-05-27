package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.PlaybookTaskEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PlaybookTaskSpecification {

    public static Specification<PlaybookTaskEntity> byPlaybookTask(@NonNull List<PlaybookTaskEntity> playbookTaskEntities) {
        return (root, query, cb) -> {
            var predicates = new ArrayList<Predicate>();

            if (!playbookTaskEntities.isEmpty()) {
                predicates.add(root.get("uuid").in(playbookTaskEntities.stream().map(PlaybookTaskEntity::getUuid).collect(toList())));
            }
            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }
}
