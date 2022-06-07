package digital.windmill.audra.graphql.type;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeletePlaybookSectionTopicPayload {
    private Node topic;
}
