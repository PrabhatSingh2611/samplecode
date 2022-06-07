package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.dao.entity.enums.PlaybookStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatchPlaybookInput {
    private UUID id;
    private String name;
    private String description;
    private ResourceInput image;
    private PlaybookStatus status;
}
