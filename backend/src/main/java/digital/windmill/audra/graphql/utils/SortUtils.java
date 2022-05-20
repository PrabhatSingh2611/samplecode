package digital.windmill.audra.graphql.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Sort;


@UtilityClass
public class SortUtils {

    public static Sort parse(String columnAndDirection) {
        var parts = columnAndDirection.split("_");
        var column = parts[0];
        var direction = parts[1];
        if (direction.equals("DESC")) {
            return Sort.by(column).descending();
        }
        return Sort.by(column);
    }

}
