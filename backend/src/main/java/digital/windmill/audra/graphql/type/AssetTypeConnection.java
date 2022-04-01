package digital.windmill.audra.graphql.type;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AssetTypeConnection {
    private List<AssetType> items;
}
