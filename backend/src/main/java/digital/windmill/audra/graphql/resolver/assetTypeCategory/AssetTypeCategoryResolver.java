package digital.windmill.audra.graphql.resolver.assetTypeCategory;

import digital.windmill.audra.graphql.facade.impl.AssetTypeFacade;
import digital.windmill.audra.graphql.type.assetType.AssetType;
import digital.windmill.audra.graphql.type.assetTypeCategory.AssetTypeCategory;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AssetTypeCategoryResolver implements GraphQLResolver<AssetTypeCategory> {

    private AssetTypeFacade assetTypeFacade;

    public List<AssetType> types(AssetTypeCategory assetTypeCategory) {
        return assetTypeFacade.findAssetTypesByCategoryUuid(assetTypeCategory.getId());
    }
}
