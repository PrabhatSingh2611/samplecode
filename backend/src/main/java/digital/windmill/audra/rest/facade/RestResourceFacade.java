package digital.windmill.audra.rest.facade;

import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.service.ResourceService;
import digital.windmill.audra.storage.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class RestResourceFacade {

    private final ResourceService service;
    private final StorageService storageService;

    @Transactional(readOnly = true)
    public Optional<Resource> findById(UUID resourceUuid) {
        return Optional.ofNullable(resourceUuid)
                .flatMap(service::findResourceByUuid)
                .map(ResourceEntity::getOuterReference)
                .flatMap(storageService::findById);
    }

}
