package digital.windmill.audra.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SpecificationUtils {
    static Order direction( final CriteriaBuilder cb, final Expression<?> e, final Sort.Direction direction ) {
        if ( direction == Direction.ASC ) {
            return cb.asc( e );
        }
        return cb.desc( e );
    }
}
