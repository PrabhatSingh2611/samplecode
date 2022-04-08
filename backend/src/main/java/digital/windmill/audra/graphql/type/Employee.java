package digital.windmill.audra.graphql.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee implements Node {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private String role;
    private ZonedDateTime birthday;
    private String position;
    private Location location;

}
