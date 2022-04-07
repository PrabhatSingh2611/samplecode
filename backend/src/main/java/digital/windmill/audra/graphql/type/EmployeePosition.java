package digital.windmill.audra.graphql.type;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeePosition implements Node{
    private UUID uuid;
    private String name;
}
