package digital.windmill.audra.graphql.resolver.assetTypeCategory;

import digital.windmill.audra.graphql.facade.impl.AssetTypeCategoryFacade;
import digital.windmill.audra.graphql.type.assetTypeCategory.AssetTypeCategoryPayload;
import digital.windmill.audra.graphql.type.assetTypeCategory.CreateAssetTypeCategoryInput;
import digital.windmill.audra.graphql.type.assetTypeCategory.PatchAssetTypeCategoryInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AssetTypeCategoryMutationResolver implements GraphQLMutationResolver {

    private AssetTypeCategoryFacade facade;

    public AssetTypeCategoryPayload createAssetTypeCategory(CreateAssetTypeCategoryInput createAssetTypeCategoryInput) {
        return AssetTypeCategoryPayload
                .builder()
                .assetTypeCategory(facade.createAssetTypeCategory(createAssetTypeCategoryInput))
                .build();
    }

    public AssetTypeCategoryPayload patchAssetTypeCategory(PatchAssetTypeCategoryInput patchAssetTypeCategoryInput){
        return AssetTypeCategoryPayload
                .builder()
                .assetTypeCategory(facade.patchAssetTypeCategory(patchAssetTypeCategoryInput))
                .build();
    }
}