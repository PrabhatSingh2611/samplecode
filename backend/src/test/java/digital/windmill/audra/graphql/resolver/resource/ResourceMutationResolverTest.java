package digital.windmill.audra.graphql.resolver.resource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.http.Part;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import digital.windmill.audra.graphql.facade.ResourceFacade;
import digital.windmill.audra.graphql.type.Resource;
import digital.windmill.audra.graphql.type.ResourcePayload;

@ExtendWith(MockitoExtension.class)
class ResourceMutationResolverTest {

    @Mock
    private ResourceFacade facade;
    @InjectMocks
    private ResourceMutationResolver resolver;

    @Test
    void shouldUploadResource(@Mock Part part, @Mock Resource resource) throws IOException {
        when(facade.storeResource(part)).thenReturn(resource);

        ResourcePayload uploadedResource = resolver.uploadResource(part);
        assertNotNull(uploadedResource);
        assertSame(resource, uploadedResource.getResource());
    }

}
