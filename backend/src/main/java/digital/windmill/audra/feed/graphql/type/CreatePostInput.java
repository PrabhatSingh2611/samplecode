package digital.windmill.audra.feed.graphql.type;

import digital.windmill.audra.graphql.type.input.NodesInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostInput {
    private String text;
    private NodesInput images;
}
