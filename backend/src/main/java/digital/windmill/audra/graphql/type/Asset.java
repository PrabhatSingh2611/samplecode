package digital.windmill.audra.graphql.type;

import digital.windmill.audra.graphql.type.assetType.AssetType;
import digital.windmill.audra.graphql.type.location.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Asset implements Node {

    private UUID id;
    private String title;
    private String serialNumber;
    private AssetType type;
    private Employee assignee;
    private ZonedDateTime waybillDate;
    private Location location;
    private String tagNumber;
    private String actionOnName;
    private ZonedDateTime nextActionDate;
    private ZonedDateTime archivedDate;
    private String comment;
}