package digital.windmill.audra.graphql.resolver.assetType;

import digital.windmill.audra.graphql.facade.impl.AssetTypeFacade;
import digital.windmill.audra.graphql.type.assetType.AssetTypePayload;
import digital.windmill.audra.graphql.type.assetType.CreateAssetTypeInput;
import digital.windmill.audra.graphql.type.assetType.UpdateAssetTypeInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AssetTypeMutationResolver implements GraphQLMutationResolver {

    private AssetTypeFacade facade;

    public AssetTypePayload createAssetType(CreateAssetTypeInput createAssetTypeInput) {
        return AssetTypePayload
                .builder()
                .assetType(facade.createAssetType(createAssetTypeInput))
                .build();
    }

    public AssetTypePayload updateAssetType(UpdateAssetTypeInput updateAssetTypeInput){
        return AssetTypePayload
                .builder()
                .assetType(facade.updateAssetType(updateAssetTypeInput))
                .build();
    }
}