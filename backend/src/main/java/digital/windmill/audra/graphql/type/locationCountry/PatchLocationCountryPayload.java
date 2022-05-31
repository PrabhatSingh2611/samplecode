package digital.windmill.audra.graphql.type.locationCountry;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatchLocationCountryPayload {
    private LocationCountry locationCountry;
}
