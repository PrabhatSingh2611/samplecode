package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.ObjectiveEntity;
import org.springframework.data.jpa.domain.Specification;

public class ObjectiveSpecification {

    public static Specification<ObjectiveEntity> allObjectives(){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.conjunction();
        };
    }
}