package digital.windmill.audra.graphql.type.input;

import lombok.Data;

import java.util.UUID;

@Data
public class PatchPlaybookSectionTopicInput {
    private UUID id;
    private String name;
    private String body;
}
