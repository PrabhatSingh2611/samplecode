package digital.windmill.audra.graphql.resolver.asset;

import digital.windmill.audra.graphql.facade.AssetFacade;
import digital.windmill.audra.graphql.type.AssetPayload;
import digital.windmill.audra.graphql.type.input.CreateAssetInput;
import digital.windmill.audra.graphql.type.input.UpdateAssetInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AssetMutationResolver implements GraphQLMutationResolver {

    private AssetFacade facade;

    public AssetPayload createAsset(CreateAssetInput createAssetInput) {
        return AssetPayload
                .builder()
                .item(facade.createAsset(createAssetInput))
                .build();
    }

    public AssetPayload updateAsset(UpdateAssetInput updateAssetInput) {
        return AssetPayload
                .builder()
                .item(facade.updateAsset(updateAssetInput))
                .build();
    }

}
