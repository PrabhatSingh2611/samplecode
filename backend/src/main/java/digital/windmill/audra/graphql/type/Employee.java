package digital.windmill.audra.graphql.type;

import java.time.ZonedDateTime;
import java.util.UUID;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String reportingManager;
    private String position;
    private String location;

}
