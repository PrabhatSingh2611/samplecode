package digital.windmill.audra.graphql.type;

import lombok.Data;

import java.util.UUID;

@Data
public class AssetType {

    private UUID uuid;
    private String title;
    private String icon;

}
