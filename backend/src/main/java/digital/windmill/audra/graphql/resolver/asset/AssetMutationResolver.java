package digital.windmill.audra.graphql.resolver.asset;

import digital.windmill.audra.graphql.facade.AssetFacade;
import digital.windmill.audra.graphql.type.Asset;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class AssetMutationResolver implements GraphQLMutationResolver {

    private AssetFacade facade;

    public Asset testMutation(UUID uuid) {
        return facade.findAssetByUuid(uuid);
    }
}
