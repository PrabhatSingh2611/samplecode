package digital.windmill.audra.graphql.type.assetTypeCategory;

import digital.windmill.audra.graphql.type.assetType.AssetTypeSort;
import digital.windmill.audra.graphql.type.input.PageInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetTypeCategoriesInput {
    private AssetTypeCategoriesWhereInput where;
    private PageInput pagination;
    private List<AssetTypeSort> sort;
}
