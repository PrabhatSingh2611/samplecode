package digital.windmill.audra.graphql.resolver.resource;

import java.io.IOException;

import javax.servlet.http.Part;

import org.springframework.stereotype.Component;

import digital.windmill.audra.graphql.facade.ResourceFacade;
import digital.windmill.audra.graphql.type.ResourcePayload;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class ResourceMutationResolver implements GraphQLMutationResolver {

    private ResourceFacade resourceFacade;

    public ResourcePayload uploadResource(Part part) throws IOException {
        return new ResourcePayload(resourceFacade.storeResource(part));
    }
}
