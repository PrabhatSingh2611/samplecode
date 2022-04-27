package digital.windmill.audra.dao;


import digital.windmill.audra.dao.entity.LocationEntity;
import org.springframework.data.jpa.domain.Specification;


public class LocationSpecification {

    public static Specification<LocationEntity> byLocation() {
        return (root, query, builder) -> {
            return builder.conjunction();
        };
    }
}