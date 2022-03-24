package digital.windmill.audra.graphql.type.input;

import lombok.Data;

@Data
public class AssetInput {
    private AssetWhereInput where;
    private PageInput page;

}
