package digital.windmill.audra.graphql.type.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubNodesWhereInput {
    private UUID uuid;
}
