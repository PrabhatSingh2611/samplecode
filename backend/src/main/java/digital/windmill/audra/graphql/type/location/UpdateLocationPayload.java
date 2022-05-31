package digital.windmill.audra.graphql.type.location;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateLocationPayload {
    private Location location;
}
