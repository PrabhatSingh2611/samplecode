package digital.windmill.audra.graphql.type.input;

import java.util.UUID;

import digital.windmill.audra.graphql.type.Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteLeaveRequestInput implements Node {
    private UUID id;
}
