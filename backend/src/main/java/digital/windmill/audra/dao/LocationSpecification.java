package digital.windmill.audra.dao;


import digital.windmill.audra.dao.entity.LocationEntity;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;


public class LocationSpecification {
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final Integer DEFAULT_PAGE_NUMBER=0;

    public static Pair<Specification<LocationEntity>, PageRequest> getAllLocations() {

        var itemsPerPage = DEFAULT_PAGE_SIZE;
        var pageNumber = DEFAULT_PAGE_NUMBER;

        var spec = Specification.where(byLocation());

        var pageable = PageRequest.of(pageNumber, itemsPerPage);
        return Pair.of(spec, pageable);
    }


    public static Specification<LocationEntity> byLocation() {
        return (root, query, builder) -> {
                return builder.conjunction();
            };
    }
}