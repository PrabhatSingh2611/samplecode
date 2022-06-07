package digital.windmill.audra.graphql.type;

import digital.windmill.audra.dao.entity.enums.PlaybookStatus;
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
    private String name;
    private String description;
    private Resource image;
    private PlaybookStatus status;
    private List<PlaybookSection> sections;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
