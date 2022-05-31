package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.LocationCountryEntity;
import digital.windmill.audra.graphql.type.locationCountry.LocationCountriesWhereInput;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class LocationCountrySpecification {

    public static Specification<LocationCountryEntity> all(LocationCountriesWhereInput where) {
        var name = Optional.ofNullable(where).map(LocationCountriesWhereInput::getName).orElse(null);
        return Specification.where(byName(name));
    }

    public static Specification<LocationCountryEntity> byName(String name) {
        return (root, query, builder) -> {
            if (name == null || name.isBlank()) {
                return builder.conjunction();
            }
            var pattern = "%" + name.toLowerCase() + "%";
            return builder.like(builder.lower(root.get("name")), pattern);
        };
    }
}