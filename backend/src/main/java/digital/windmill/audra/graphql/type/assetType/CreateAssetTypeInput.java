package digital.windmill.audra.graphql.type.assetType;

import digital.windmill.audra.graphql.type.input.NodeInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAssetTypeInput {
    private NodeInput category;
    private String name;
    private String iconName;
}
