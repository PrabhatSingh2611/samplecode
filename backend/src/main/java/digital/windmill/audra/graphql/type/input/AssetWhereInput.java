package digital.windmill.audra.graphql.type.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetWhereInput {
    private String query;
    private NodesInput type;
    private NodesInput location;
    private NodesInput assignee;
    private Boolean archived;
}