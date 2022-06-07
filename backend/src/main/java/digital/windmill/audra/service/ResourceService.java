package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.dao.repository.ResourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
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

    public ResourceEntity save(ResourceEntity resourceEntity) {
        return repository.save(resourceEntity);
    }

    public List<ResourceEntity> findByUuids(Collection<UUID> uuids) {
        return repository.findByUuids(uuids);
    }

}
