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
public class AssetInput implements Node {
    private UUID id;
}
