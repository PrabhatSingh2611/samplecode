package digital.windmill.audra.storage.impl;

import digital.windmill.audra.storage.StorableObject;
import digital.windmill.audra.storage.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class AzureBlobStorageServiceImpl implements StorageService {
    @Value("${spring.cloud.azure.storage.blob.container.resources}")
    private String containerResources;
    private final ResourceLoader resourceLoader;

    public String store(StorableObject objectToStore) {
        log.info("Uploading to Azure Blob Storage {}", objectToStore);
        var resourceId =  UUID.randomUUID().toString();
        Resource storageBlobResource = resourceLoader.getResource("azure-blob://" + containerResources + "/" + resourceId);
        try (OutputStream os = ((WritableResource) storageBlobResource).getOutputStream()) {
            objectToStore.getStream().transferTo(os);
            return resourceId;
        } catch (IOException e) {
            log.warn("Can not write file {}", objectToStore.getFileName(), e);
            return null;
        }
    }

    public Optional<Resource> findById(String resourceId) {
        return Optional.of(resourceId)
                .map(id -> "azure-blob://" + containerResources + "/" + id)
                .map(resourceLoader::getResource);
    }

}
