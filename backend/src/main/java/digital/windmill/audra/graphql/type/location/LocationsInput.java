package digital.windmill.audra.graphql.type.location;

import digital.windmill.audra.graphql.type.input.PageInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationsInput {
    private LocationsWhereInput where;
    private PageInput pagination;
    private List<LocationSort> sort;
}
