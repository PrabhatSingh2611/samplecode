package digital.windmill.audra.graphql.type;

import lombok.Data;

import java.util.UUID;

@Data
public class ReorderPlaybookSectionInput {
    private UUID id;
    private UUID afterId;
}
