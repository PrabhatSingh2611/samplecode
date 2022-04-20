package digital.windmill.audra.graphql.resolver.asset;

import digital.windmill.audra.graphql.facade.AssetTypeFacade;
import digital.windmill.audra.graphql.type.AssetTypePayload;
import digital.windmill.audra.graphql.type.input.CreateAssetTypeInput;
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
                .item(facade.createAssetType(createAssetTypeInput))
                .build();
    }
}