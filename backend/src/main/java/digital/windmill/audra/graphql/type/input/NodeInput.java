package digital.windmill.audra.graphql.type.input;

import java.util.UUID;

import digital.windmill.audra.graphql.type.Node;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NodeInput implements Node {

    private UUID uuid;

    public static NodeInput of(String uuid) {
        return new NodeInput(UUID.fromString(uuid));
    }

}
