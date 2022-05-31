package digital.windmill.audra.graphql.type.locationCountry;

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
public class LocationCountriesInput {
    private LocationCountriesWhereInput where;
    private PageInput pagination;
    private List<LocationCountrySort> sort;
}
