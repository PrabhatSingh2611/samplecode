package digital.windmill.audra.graphql.resolver.asset;

import digital.windmill.audra.graphql.facade.AssetTypeFacade;
import digital.windmill.audra.graphql.type.AssetTypePayload;
import digital.windmill.audra.graphql.type.input.AssetTypeInput;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AssetTypeResolver implements GraphQLQueryResolver {

    private AssetTypeFacade facade;

    public AssetTypePayload assetType(AssetTypeInput input) {
        return AssetTypePayload
                .builder()
                .item(facade.findAssetTypeByUuid(input.getUuid()))
                .build();
    }
}
