package digital.windmill.audra.graphql.type.input;

import lombok.Data;

import java.util.UUID;

@Data
public class ReorderPlaybookSectionTopicInput {
    private UUID id;
    private UUID afterId;
    private UUID sectionId;
}
