package digital.windmill.audra.graphql.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Playbook implements Node {

    private UUID id;
    private String title;
    private Resource image;
    private List<PlaybookStep> steps;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
