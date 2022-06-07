package digital.windmill.audra.graphql.type;

import lombok.Data;

import java.util.UUID;

@Data
public class PatchPlaybookSectionInput {
    private UUID id;
    private String name;
}
