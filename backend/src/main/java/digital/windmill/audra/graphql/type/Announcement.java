package digital.windmill.audra.graphql.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Announcement implements Node {
    private UUID uuid;
    private String body;
    private ZonedDateTime createdAt;
}
