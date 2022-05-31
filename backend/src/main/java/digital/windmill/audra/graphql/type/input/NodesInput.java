package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.graphql.type.Nodes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NodesInput implements Nodes {

    private List<UUID> ids;

    public static NodesInput of(UUID... ids) {
        return new NodesInput(List.of(ids));
    }

    public static NodesInput of(String... ids) {
        return new NodesInput(Arrays.stream(ids).map(UUID::fromString).toList());
    }
}
