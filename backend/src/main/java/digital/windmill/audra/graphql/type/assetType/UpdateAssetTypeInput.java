package digital.windmill.audra.graphql.type.assetType;


import digital.windmill.audra.graphql.type.input.NodeInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAssetTypeInput {

    private UUID id;
    private NodeInput category;
    private String name;
    private String iconName;

}
