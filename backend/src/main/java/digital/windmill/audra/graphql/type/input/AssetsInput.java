package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.graphql.type.enums.AssetSortEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetsInput {
    private AssetWhereInput where;
    private PageInput pagination;
    private List<AssetSortEnum> sort;
}
