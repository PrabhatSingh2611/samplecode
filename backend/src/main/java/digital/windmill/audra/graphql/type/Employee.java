package digital.windmill.audra.graphql.type;

import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class Employee implements Node {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private String role;
    private ZonedDateTime birthday;
    private String position;
    private String location;

}
