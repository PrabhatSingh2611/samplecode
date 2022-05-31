package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.graphql.type.Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NodeInput implements Node {

    private UUID id;

    public static NodeInput of(UUID id) {
        return new NodeInput(id);
    }

    public static NodeInput of(String id) {
        return new NodeInput(UUID.fromString(id));
    }

}
