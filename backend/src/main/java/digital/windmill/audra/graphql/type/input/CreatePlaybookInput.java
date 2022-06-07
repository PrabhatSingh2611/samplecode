package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.dao.entity.enums.PlaybookStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlaybookInput {
    private String name;
    private String description;
    private ResourceInput image;
    private PlaybookStatus status;
}
