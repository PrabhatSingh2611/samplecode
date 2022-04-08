package digital.windmill.audra.graphql.type;

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

    private UUID uuid;
    private String title;
    private String serial;
    private AssetType type;
    private Employee employee;
    private ZonedDateTime archivedDate;
    private ZonedDateTime purchasedDate;

}