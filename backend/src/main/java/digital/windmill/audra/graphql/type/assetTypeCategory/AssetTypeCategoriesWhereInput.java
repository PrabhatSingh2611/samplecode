package digital.windmill.audra.graphql.type.assetTypeCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetTypeCategoriesWhereInput {
    private String name;
}
