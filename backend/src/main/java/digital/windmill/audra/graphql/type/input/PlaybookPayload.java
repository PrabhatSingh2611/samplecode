package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.graphql.type.Playbook;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaybookPayload {
    private Playbook playbook;
}
