package digital.windmill.audra.graphql.resolver.asset;

import digital.windmill.audra.graphql.type.AssetPayload;
import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.input.AssetInput;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import org.springframework.stereotype.Component;

import digital.windmill.audra.graphql.facade.AssetFacade;
import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.graphql.type.input.AssetsInput;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AssetResolver implements GraphQLQueryResolver {

    private AssetFacade facade;

    public AssetPayload asset(AssetInput input) {

        return AssetPayload
                .builder()
                .item(facade.findAssetByUuid(input.getUuid()))
                .build();
    }

    public ConnectionPayload<Asset> assets(AssetsInput input) {
        return ConnectionUtils.buildPayload(facade.findAllAssets(input));
    }
}
