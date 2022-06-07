package digital.windmill.audra.graphql.type.input;

import lombok.Data;

@Data
public class CreatePlaybookSectionInput {
    private NodeInput playbook;
    private String name;
}
