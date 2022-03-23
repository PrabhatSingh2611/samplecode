package digital.windmill.audra.graphql.type;

import lombok.Data;

@Data
public class AssetInput {
    private AssetWhereInput where;
    private PageInput page;

}
