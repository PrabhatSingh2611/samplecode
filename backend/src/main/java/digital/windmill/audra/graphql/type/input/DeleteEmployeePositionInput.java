package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.graphql.type.Node;
import lombok.Data;

import java.util.UUID;

@Data
public class DeleteEmployeePositionInput implements Node {
    private UUID uuid;
}
