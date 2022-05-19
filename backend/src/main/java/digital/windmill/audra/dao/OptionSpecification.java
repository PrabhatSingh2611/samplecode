package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.OptionEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class OptionSpecification {

    public static Specification<OptionEntity> byOptions(@NonNull List<OptionEntity> options) {
        return (root, query, cb) -> {
            var predicates = new ArrayList<Predicate>();

            if (!options.isEmpty()) {
                predicates.add(root.get("uuid").in(options.stream().map(OptionEntity::getUuid).toList()));
            }
            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }
}
