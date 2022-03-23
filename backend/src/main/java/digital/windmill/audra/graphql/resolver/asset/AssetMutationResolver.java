package digital.windmill.audra.graphql.resolver.asset;

import org.springframework.stereotype.Component;

import digital.windmill.audra.graphql.facade.AssetFacade;
import digital.windmill.audra.graphql.type.Asset;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AssetMutationResolver implements GraphQLMutationResolver {

    private AssetFacade facade;

    public Asset testMutation(Long id) {
        return facade.findAssetById(id);
    }
}
