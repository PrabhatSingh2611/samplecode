package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.graphql.mapper.ResourceMapper;
import digital.windmill.audra.graphql.type.Resource;
import digital.windmill.audra.properties.ResourceProperties;
import digital.windmill.audra.service.ResourceService;
import digital.windmill.audra.service.impl.ThumbnailServiceImpl;
import digital.windmill.audra.storage.StorableObject;
import digital.windmill.audra.storage.StorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
@Slf4j
public class ResourceFacade {

    private ResourceService service;
    private ResourceMapper mapper;
    private StorageService storageService;
    private ThumbnailServiceImpl thumbnailService;
    private ResourceProperties resourceProperty;

    @Transactional(readOnly = true)
    public Resource findResourceByUuid(UUID uuid) {
        return service.findResourceByUuid(uuid)
                .map(mapper::map)
                .orElse(null);
    }

    @Transactional
    public Resource storeResource(Part file, Part thumbnail) throws IOException {
        StorableObject resourceToStore = StorableObject.builder()
                .contentType(file.getContentType())
                .fileName(file.getSubmittedFileName())
                .stream(file.getInputStream())
                .build();
        String resourceReference = storageService.store(resourceToStore);
        String thumbnailReference = storeThumbnail(thumbnail != null ? thumbnail : file);
        ResourceEntity newResource = service.createResourceByReference(resourceReference, thumbnailReference);
        return mapper.map(newResource);
    }

    private String storeThumbnail(Part file) {
        var thumbProps = resourceProperty.getThumbnail();
        return Optional.ofNullable(file)
                .map(f -> thumbnailService.generateThumbnail(f, thumbProps.getHeight(), thumbProps.getWidth()))
                .map(thumbnailFile -> {
                    try {
                        return StorableObject.builder()
                                .fileName(thumbnailFile.getName())
                                .contentType(Files.probeContentType(thumbnailFile.toPath()))
                                .stream(FileUtils.openInputStream(thumbnailFile))
                                .build();
                    } catch (IOException e) {
                        log.error("Can not create StorableObject for thumbnail {}", thumbnailFile, e);
                        throw new RuntimeException(e);
                    }
                })
                .map(storageService::store)
                .orElse(null);
    }

}
