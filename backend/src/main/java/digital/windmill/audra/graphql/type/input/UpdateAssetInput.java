package digital.windmill.audra.graphql.type.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAssetInput {
    private UUID id;
    private String title;
    private String serialNumber;
    private NodeInput type;
    private NodeInput assignee;
    private NodeInput location;
    private ZonedDateTime archivedDate;
    private ZonedDateTime waybillDate;
    private ZonedDateTime nextActionDate;
    private String actionOnName;
    private String comment;
}
