package digital.windmill.audra.graphql.type;

import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class Asset implements Node {

    private UUID uuid;
    private String title;
    private String serial;
    private AssetType type;
    private Employee employee;
    private ZonedDateTime archivedDate;
    private ZonedDateTime purchasedDate;

}