package digital.windmill.audra.graphql.type.locationCountry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateLocationCountryInput {
    private String name;
    private String iconName;
}
