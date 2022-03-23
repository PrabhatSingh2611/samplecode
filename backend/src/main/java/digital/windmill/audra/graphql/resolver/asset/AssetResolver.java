package digital.windmill.audra.graphql.resolver.asset;

import org.springframework.stereotype.Component;

import digital.windmill.audra.graphql.facade.AssetFacade;
import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.graphql.type.AssetConnectionPayload;
import digital.windmill.audra.graphql.type.AssetInput;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AssetResolver implements GraphQLQueryResolver {

    private AssetFacade facade;

    public Asset asset(Long id) {
        return facade.findAssetById(id);
    }

    public AssetConnectionPayload assets(AssetInput input) {
        return new AssetConnectionPayload();
    }
}
