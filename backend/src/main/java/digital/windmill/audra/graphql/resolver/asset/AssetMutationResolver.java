package digital.windmill.audra.graphql.resolver.asset;

import digital.windmill.audra.graphql.facade.impl.AssetFacadeImpl;
import digital.windmill.audra.graphql.type.Asset;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class AssetMutationResolver implements GraphQLMutationResolver {

    private AssetFacadeImpl facade;

    public Asset testMutation(UUID uuid) {
        return facade.findAssetByUuid(uuid);
    }
}
