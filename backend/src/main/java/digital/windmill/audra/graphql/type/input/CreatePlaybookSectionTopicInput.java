package digital.windmill.audra.graphql.type.input;

import lombok.Data;

@Data
public class CreatePlaybookSectionTopicInput {
    private String name;
    private NodeInput section;
}
