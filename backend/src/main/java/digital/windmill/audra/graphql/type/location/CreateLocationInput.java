package digital.windmill.audra.graphql.type.location;

import digital.windmill.audra.graphql.type.input.NodeInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateLocationInput {
    private NodeInput country;
    private String name;
}
