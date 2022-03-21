package digital.windmill.audra.graphql.resolver.asset;

import java.util.List;
import org.springframework.stereotype.Component;

import digital.windmill.audra.graphql.facade.AssetFacade;
import digital.windmill.audra.graphql.type.Asset;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AssetResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    private AssetFacade facade;

    public Asset asset(Long id) {
        return facade.findAssetById(id);
    }

    public Asset testMutation(Long id) {
        return facade.findAssetById(id);
    }

    public List<Asset> assets() {
        return List.of();
    }
}
