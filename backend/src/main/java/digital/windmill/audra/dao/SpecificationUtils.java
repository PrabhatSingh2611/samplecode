package digital.windmill.audra.dao;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;

@UtilityClass
public class SpecificationUtils {
    public static Order direction( final CriteriaBuilder cb, final Expression<?> e, final Sort.Direction direction ) {
        if ( direction == Direction.ASC ) {
            return cb.asc( e );
        }
        return cb.desc( e );
    }
}
