package digital.windmill.audra.graphql.resolver.resource;

import org.springframework.stereotype.Component;

import digital.windmill.audra.graphql.facade.ResourceFacade;
import digital.windmill.audra.graphql.type.ResourcePayload;
import digital.windmill.audra.graphql.type.input.ResourceInput;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ResourceResolver implements GraphQLQueryResolver {

    private ResourceFacade facade;

    public ResourcePayload resource(ResourceInput input) {
        return new ResourcePayload(facade.findResourceByUuid(input.getId()));
    }

}
