package digital.windmill.audra.storage;

import org.springframework.core.io.Resource;

import java.util.Optional;

public interface StorageService {

    String store(StorableObject objectToStore);

    Optional<Resource> findById(String resourceId);

}
