package digital.windmill.audra.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.dao.repository.ResourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ResourceService {

    private final ResourceRepository repository;

    public Optional<ResourceEntity> findResourceByUuid(UUID uuid) {
        return Optional.ofNullable(uuid)
                .flatMap(repository::findByUuid);
    }

    public ResourceEntity createResourceByReference(String resourceReference, String thumbnailReference) {
        ResourceEntity resourceEntity = new ResourceEntity();
        resourceEntity.setOuterReference(resourceReference);
        resourceEntity.setUuid(UUID.randomUUID());

        if (thumbnailReference != null) {
            var thumbnailEntity = new ResourceEntity();
            thumbnailEntity.setUuid(UUID.randomUUID());
            thumbnailEntity.setOuterReference(thumbnailReference);
            resourceEntity.setThumbnail(thumbnailEntity);
        }

        return repository.save(resourceEntity);
    }

    public List<ResourceEntity> findByUuids(Collection<UUID> uuids){
        return repository.findByUuids(uuids);
    }

}
