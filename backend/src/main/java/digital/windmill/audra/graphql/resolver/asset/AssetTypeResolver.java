package digital.windmill.audra.graphql.resolver.asset;

import digital.windmill.audra.graphql.facade.AssetTypeFacade;
import digital.windmill.audra.graphql.type.AssetType;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class AssetTypeResolver implements GraphQLQueryResolver {

    private AssetTypeFacade facade;

    public AssetType assetType(UUID uuid) {
        return facade.findAssetTypeByUuid(uuid);
    }
}
