package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.PolicyEntity;
import digital.windmill.audra.graphql.type.input.PoliciesInput;
import digital.windmill.audra.graphql.type.input.PoliciesWhereInput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Optional;

public class PolicySpecification {
    public static Specification<PolicyEntity> byPoliciesInput(@NonNull PoliciesInput input) {
        return (root, query, cb) -> {
            var predicates = new ArrayList<Predicate>();

            Optional.of(input)
                    .map(PoliciesInput::getWhere)
                    .map(PoliciesWhereInput::getQuery)
                    .map(q -> cb.equal(root.get("title"), q))
                    .ifPresent(predicates::add);

            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }
}
