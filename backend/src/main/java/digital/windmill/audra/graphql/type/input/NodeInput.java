package digital.windmill.audra.graphql.type.input;

import java.util.UUID;

import digital.windmill.audra.graphql.type.Node;
import lombok.Data;

@Data
public class NodeInput implements Node {

    private UUID uuid;

}
