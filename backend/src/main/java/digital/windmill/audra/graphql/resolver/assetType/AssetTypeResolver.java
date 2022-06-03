package digital.windmill.audra.graphql.resolver.assetType;

import digital.windmill.audra.graphql.facade.impl.AssetTypeFacade;
import digital.windmill.audra.graphql.type.assetType.AssetType;
import digital.windmill.audra.graphql.type.assetType.AssetTypePayload;
import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.assetType.AssetTypeInput;
import digital.windmill.audra.graphql.type.assetType.AssetTypesInput;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
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
                .assetType(facade.findAssetTypeByUuid(input.getId()))
                .build();
    }

    public ConnectionPayload<AssetType> getAssetTypes(AssetTypesInput input) {
        return ConnectionUtils.buildPayload(facade.getAssetTypes(input));

    }
}