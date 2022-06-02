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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
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
        String fileReference = storeFile(file);

        var resourceEntity = new ResourceEntity();
        resourceEntity.setUuid(UUID.randomUUID());
        resourceEntity.setOuterReference(fileReference);
        resourceEntity.setMimeType(file.getContentType());

        if (thumbnail != null) {
            var thumbnailReference = this.storeFile(thumbnail);

            var thumbnailEntity = new ResourceEntity();
            thumbnailEntity.setUuid(UUID.randomUUID());
            thumbnailEntity.setOuterReference(thumbnailReference);
            thumbnailEntity.setMimeType(thumbnail.getContentType());

            resourceEntity.setThumbnail(thumbnailEntity);
        }

        // TODO: implement auto-generate thumbnail
//        var generatedThumbnailFile = generateThumbnail(file);
//        if (generatedThumbnailFile != null) {
//            var thumbnailReference = this.storeFile(generatedThumbnailFile);
//
//            var thumbnailEntity = new ResourceEntity();
//            thumbnailEntity.setUuid(UUID.randomUUID());
//            thumbnailEntity.setOuterReference(thumbnailReference);
//            thumbnailEntity.setMimeType("image/jpg");
//
//            resourceEntity.setThumbnail(thumbnailEntity);
//        }


        return mapper.map(service.save(resourceEntity));
    }

    private String storeFile(Part file) throws IOException {
        StorableObject resourceToStore = StorableObject.builder()
                .contentType(file.getContentType())
                .fileName(file.getSubmittedFileName())
                .stream(file.getInputStream())
                .build();

        return storageService.store(resourceToStore);
    }

    private File generateThumbnail(Part file) {
        var thumbProps = resourceProperty.getThumbnail();
        return Optional.ofNullable(file)
                .map(f -> thumbnailService.generateThumbnail(f, thumbProps.getHeight(), thumbProps.getWidth()))
                .orElse(null);
    }

}
