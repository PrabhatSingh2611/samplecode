package digital.windmill.audra.graphql.type;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteAnnouncementPayload {
    private Node announcement;
}
