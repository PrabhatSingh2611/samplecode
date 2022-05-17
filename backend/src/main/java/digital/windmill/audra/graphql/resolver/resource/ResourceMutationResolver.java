package digital.windmill.audra.graphql.resolver.resource;

import digital.windmill.audra.graphql.facade.ResourceFacade;
import digital.windmill.audra.graphql.type.ResourcePayload;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.Part;
import java.io.IOException;


@Component
@AllArgsConstructor
public class ResourceMutationResolver implements GraphQLMutationResolver {

    private ResourceFacade resourceFacade;

    public ResourcePayload uploadResource(Part file, Part thumbnail) throws IOException {
        return new ResourcePayload(resourceFacade.storeResource(file, thumbnail));
    }
}
