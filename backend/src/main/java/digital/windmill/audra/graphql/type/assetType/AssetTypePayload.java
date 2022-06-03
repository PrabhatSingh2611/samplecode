package digital.windmill.audra.graphql.type.assetType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssetTypePayload {
    private AssetType assetType;
}
