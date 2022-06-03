package digital.windmill.audra.graphql.type.assetTypeCategory;

import digital.windmill.audra.graphql.type.Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetTypeCategory implements Node {

    private UUID id;
    private String name;

}
