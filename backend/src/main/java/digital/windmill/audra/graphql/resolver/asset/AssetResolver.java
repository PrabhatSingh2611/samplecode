package digital.windmill.audra.graphql.resolver.asset;

import digital.windmill.audra.graphql.type.ExtendedConnection;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import org.springframework.stereotype.Component;

import digital.windmill.audra.graphql.facade.AssetFacade;
import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.graphql.type.input.AssetInput;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AssetResolver implements GraphQLQueryResolver {

    private AssetFacade facade;

    public Asset asset(Long id) {
        return facade.findAssetById(id);
    }

    public ExtendedConnection<Asset> assets(AssetInput input) {
        return ConnectionUtils.buildNodeConnection(facade.findAllAssets());
    }
}
