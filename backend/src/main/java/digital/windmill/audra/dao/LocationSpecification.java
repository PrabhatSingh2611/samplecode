package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.type.input.NodeInput;
import digital.windmill.audra.graphql.type.location.LocationsWhereInput;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class LocationSpecification {

    public static Specification<LocationEntity> all(LocationsWhereInput where) {
        var name = Optional.ofNullable(where).map(LocationsWhereInput::getName).orElse(null);
        var country = Optional.ofNullable(where).map(LocationsWhereInput::getCountry).orElse(null);
        return Specification.where(
                byName(name).and(byCountry(country)));
    }

    public static Specification<LocationEntity> byName(String name) {
        return (root, query, builder) -> {
            if (name == null || name.isBlank()) {
                return builder.conjunction();
            }
            var pattern = "%" + name.toLowerCase() + "%";
            return builder.like(builder.lower(root.get("name")), pattern);
        };
    }

    public static Specification<LocationEntity> byCountry(NodeInput countryInput) {
        return (root, query, builder) -> {
            if (countryInput == null) {
                return builder.conjunction();
            }
            var country = root.join("country");
            return builder.equal(country.get("uuid"),  countryInput.getId());
        };
    }
}