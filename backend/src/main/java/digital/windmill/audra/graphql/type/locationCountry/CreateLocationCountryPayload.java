package digital.windmill.audra.graphql.type.locationCountry;

import digital.windmill.audra.graphql.type.location.Location;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateLocationCountryPayload {
    private Location location;
}
