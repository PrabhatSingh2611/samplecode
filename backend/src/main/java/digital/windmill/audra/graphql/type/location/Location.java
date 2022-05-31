package digital.windmill.audra.graphql.type.location;

import digital.windmill.audra.graphql.type.Node;
import digital.windmill.audra.graphql.type.locationCountry.LocationCountry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location implements Node {
    private UUID id;
    private String name;
    private LocationCountry country;
}
