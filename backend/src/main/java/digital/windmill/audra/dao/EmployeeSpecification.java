package digital.windmill.audra.dao;


import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.input.*;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class EmployeeSpecification {
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    public static Pair<Specification<EmployeeEntity>, PageRequest> assets(EmployeeInput input) {
        var location = Optional.ofNullable(input).map(EmployeeInput::getWhere).map(EmployeeWhereInput::getLocation).orElse(null);

        var itemsPerPage = Optional.ofNullable(input).map(EmployeeInput::getPagination).map(PageInput::getItemsPerPage).orElse(DEFAULT_PAGE_SIZE);
        var pageNumber = Optional.ofNullable(input).map(EmployeeInput::getPagination).map(PageInput::getPageNumber).orElse(0);

        var spec = Specification.where(
                        byLocation(location));


        var pageable = PageRequest.of(pageNumber, itemsPerPage);
        return Pair.of(spec, pageable);
    }



    public static Specification<EmployeeEntity> byLocation(SubNodesWhereInput location) {
        return (root, query, builder) -> {
            if (location == null || location.getUuid() == null) {
                return builder.conjunction();
            }
            var employeeJoin = root.join("employee");
            return builder.equal(employeeJoin.get("uuid"), location.getUuid());
        };
    }

}
