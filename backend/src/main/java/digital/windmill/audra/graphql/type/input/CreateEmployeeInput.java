package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeInput {
    private String firstName;
    private String lastName;
    private EmployeeRole role;
    private ZonedDateTime birthday;
    private UUID reportingManager;
    private UUID position;
    private UUID location;
}
