package digital.windmill.audra.graphql.type.input;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UpdateEmployeePositionInput {
    private UUID id;
    private String name;
}
