package digital.windmill.audra.graphql.resolver.asset;

import digital.windmill.audra.graphql.facade.impl.AssetTypeFacadeImpl;
import digital.windmill.audra.graphql.type.AssetTypePayload;
import digital.windmill.audra.graphql.type.input.AssetTypeInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AssetTypeMutationResolver implements GraphQLMutationResolver {

    private AssetTypeFacadeImpl facade;

    public AssetTypePayload createAssetType(AssetTypeInput assetTypeInput) {
        return AssetTypePayload
                .builder()
                .item(facade.createAssetType(assetTypeInput))
                .build();
    }
}