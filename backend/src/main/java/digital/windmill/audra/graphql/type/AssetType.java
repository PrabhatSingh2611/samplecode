package digital.windmill.audra.graphql.type;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AssetType implements Node{

    private UUID uuid;
    private String title;
    private String icon;

}
