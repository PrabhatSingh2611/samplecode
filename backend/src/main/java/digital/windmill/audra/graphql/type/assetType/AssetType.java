package digital.windmill.audra.graphql.type.assetType;

import digital.windmill.audra.graphql.type.Node;
import digital.windmill.audra.graphql.type.assetTypeCategory.AssetTypeCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetType implements Node {

    private UUID id;
    private AssetTypeCategory category;
    private String name;
    private String iconName;
    private ZonedDateTime createdAt;

}
