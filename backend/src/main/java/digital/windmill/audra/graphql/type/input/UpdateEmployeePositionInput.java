package digital.windmill.audra.graphql.type.input;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateEmployeePositionInput {
    private UUID uuid;
    private String name;
}
