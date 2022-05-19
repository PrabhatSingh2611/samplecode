package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.graphql.mapper.ResourceMapper;
import digital.windmill.audra.graphql.type.Resource;
import digital.windmill.audra.properties.ResourceProperties;
import digital.windmill.audra.service.ResourceService;
import digital.windmill.audra.service.impl.ThumbnailServiceImpl;
import digital.windmill.audra.storage.StorableObject;
import digital.windmill.audra.storage.StorageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResourceFacadeTest {

    private static final UUID RESOURCE_UUID = UUID.randomUUID();
    private static final String RESOURCE_REFERENCE = "reference";
    private static final String RESOURCE_FILE_NAME = "filename.jpg";
    private static final String RESOURCE_CONTENT_TYPE = "application/octet-stream";

    @Mock
    private ResourceService service;
    @Mock
    private ResourceMapper mapper;
    @Mock
    private StorageService storageService;
    @Mock
    private ResourceProperties resourceProperties;
    @Mock
    private ThumbnailServiceImpl thumbnailService;

    @InjectMocks
    private ResourceFacade facade;

    @Test
    void shouldFindResourceByUuid(@Mock ResourceEntity resourceEntity,
                                  @Mock Resource resource) {
        when(service.findResourceByUuid(RESOURCE_UUID)).thenReturn(Optional.of(resourceEntity));
        when(mapper.map(resourceEntity)).thenReturn(resource);

        Resource actualResult = facade.findResourceByUuid(RESOURCE_UUID);
        assertNotNull(actualResult);
        assertEquals(resource, actualResult);
    }

    @Test
    void shouldNotFindResourceByUuid() {
        when(service.findResourceByUuid(RESOURCE_UUID)).thenReturn(Optional.empty());

        Resource actualResult = facade.findResourceByUuid(RESOURCE_UUID);
        assertNull(actualResult);
    }

    @Test
    void shouldStoreResource(
            @Mock Part part,
            @Mock InputStream inputStream,
            @Mock ResourceEntity resourceEntity,
            @Mock Resource resource,
            @Mock ResourceProperties.Thumbnail thumbnail
    ) throws IOException {
        StorableObject objectToStore = StorableObject.builder()
                .stream(inputStream)
                .fileName(RESOURCE_FILE_NAME)
                .contentType(RESOURCE_CONTENT_TYPE)
                .build();

        when(part.getSubmittedFileName()).thenReturn(RESOURCE_FILE_NAME);
        when(part.getInputStream()).thenReturn(inputStream);
        when(part.getContentType()).thenReturn(RESOURCE_CONTENT_TYPE);
        when(storageService.store(objectToStore)).thenReturn(RESOURCE_REFERENCE);
        when(service.createResourceByReference(RESOURCE_REFERENCE, null)).thenReturn(resourceEntity);
        when(mapper.map(resourceEntity)).thenReturn(resource);
        when(resourceProperties.getThumbnail()).thenReturn(thumbnail);
        when(resourceProperties.getThumbnail().getHeight()).thenReturn(100);
        when(resourceProperties.getThumbnail().getWidth()).thenReturn(100);

        Resource actualResult = facade.storeResource(part, null);
        assertNotNull(actualResult);
        assertSame(resource, actualResult);

    }

}
