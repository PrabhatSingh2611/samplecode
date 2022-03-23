package digital.windmill.audra.graphql.type;

import java.util.List;

public class AssetConnectionPayload extends ConnectionPayload<Asset> {

    @Override
    public List<Asset> getItems() {
        return List.of();
    }
    
    @Override
    public PageInfo getPageInfo() {
        return EMPTY;
    }
    
    @Override
    public Long getTotalItems() {
        return (long) -1;
    }
}
