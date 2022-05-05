package digital.windmill.audra.graphql.facade;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.Part;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.graphql.mapper.ResourceMapper;
import digital.windmill.audra.graphql.type.Resource;
import digital.windmill.audra.service.ResourceService;
import digital.windmill.audra.storage.StorableObject;
import digital.windmill.audra.storage.StorageService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ResourceFacade {

    private ResourceService service;
    private ResourceMapper mapper;
    private StorageService storageService;

    @Transactional(readOnly = true)
    public Resource findResourceByUuid(UUID uuid) {
        return service.findResourceByUuid(uuid)
                .map(mapper::map)
                .orElse(null);
    }

    @Transactional
    public Resource storeResource(Part part) throws IOException {
        StorableObject resourceToStore = StorableObject.builder()
            .contentType(part.getContentType())
            .fileName(part.getSubmittedFileName())
            .stream(part.getInputStream())
            .build();
        String resourceReference = storageService.store(resourceToStore);

        ResourceEntity newResource = service.createResourceByReference(resourceReference);
        return mapper.map(newResource);
    }

}
