package digital.windmill.audra.graphql.resolver.assetTypeCategory;

import digital.windmill.audra.graphql.facade.impl.AssetTypeCategoryFacade;
import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.assetTypeCategory.AssetTypeCategoriesInput;
import digital.windmill.audra.graphql.type.assetTypeCategory.AssetTypeCategory;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AssetTypeCategoryQueryResolver implements GraphQLQueryResolver {

    private AssetTypeCategoryFacade facade;

    public ConnectionPayload<AssetTypeCategory> getAssetTypeCategories(AssetTypeCategoriesInput input) {
        return ConnectionUtils.buildPayload(facade.getAssetTypeCategories(input));
    }
}