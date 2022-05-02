package digital.windmill.audra.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.dao.repository.ResourceRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ResourceService {

    private final ResourceRepository repository;

    public Optional<ResourceEntity> findResourceByUuid(UUID uuid) {
        return Optional.ofNullable(uuid)
                .flatMap(repository::findByUuid);
    }

    public ResourceEntity createResourceByReference(String resourceReference) {
        ResourceEntity entity = new ResourceEntity();
        entity.setOuterReference(resourceReference);
        entity.setUuid(UUID.randomUUID());
        return repository.save(entity);
    }

}
