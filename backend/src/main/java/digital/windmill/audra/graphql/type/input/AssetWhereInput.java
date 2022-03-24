package digital.windmill.audra.graphql.type.input;

import lombok.Data;

@Data
public class AssetWhereInput {
    private NodeInput employee;
    private Boolean archived;
    private NodeInput type;
}
