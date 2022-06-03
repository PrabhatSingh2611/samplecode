package digital.windmill.audra.graphql.type.assetTypeCategory;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssetTypeCategoryPayload {
    private AssetTypeCategory assetTypeCategory;
}
