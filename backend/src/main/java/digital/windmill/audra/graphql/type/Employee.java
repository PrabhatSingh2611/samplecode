package digital.windmill.audra.graphql.type;

import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.graphql.type.location.Location;
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

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private EmployeeRole role;
    private ZonedDateTime birthday;
    private Employee reportingManager;
    private EmployeePosition position;
    private Location location;
    private String avatar;

}
