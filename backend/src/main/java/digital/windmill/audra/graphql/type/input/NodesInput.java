package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.graphql.type.Nodes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NodesInput implements Nodes {

    private List<UUID> uuids;

    public static NodesInput of(List<String> uuids){
        return new NodesInput(uuids.stream()
                .map(UUID::fromString)
                .toList()
        );
    }
}
