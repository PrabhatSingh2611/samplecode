package digital.windmill.audra.graphql.type;

import java.util.UUID;

import lombok.Data;

@Data
public class NodeInput implements Node {

    private UUID uuid;

}
