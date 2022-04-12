package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.graphql.type.Node;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DeleteEmployeePositionInput implements Node {
    private UUID uuid;
}
