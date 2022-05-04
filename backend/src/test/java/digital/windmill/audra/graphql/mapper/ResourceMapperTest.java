package digital.windmill.audra.graphql.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.graphql.type.Resource;

@ExtendWith(MockitoExtension.class)
class ResourceMapperTest {

    private static final UUID RESOURCE_UUID = UUID.randomUUID();
    private static final String RESOURCE_PATH = "http://resource_path";
    private static final String RESOURCE_REFERENCE = "res_reference";
    private static final String THUMBNAIL_REFERENCE = "res_reference";
    private static final String RESOURCE_URL = RESOURCE_PATH + "/" + RESOURCE_REFERENCE;
    private static final String THUMBNAIL_URL = RESOURCE_PATH + "/" + THUMBNAIL_REFERENCE;

    private ResourceMapper mapper = new ResourceMapperImpl();

    @Test
    void shouldMap(@Mock( answer = Answers.RETURNS_DEEP_STUBS ) ResourceEntity entity) {
        mapper.setUrlPath(RESOURCE_PATH);
        when(entity.getUuid()).thenReturn(RESOURCE_UUID);
        when(entity.getOuterReference()).thenReturn(RESOURCE_REFERENCE);
        when(entity.getThumbnail().getOuterReference()).thenReturn(THUMBNAIL_REFERENCE);

        Resource actual = mapper.map(entity);
        assertEquals(RESOURCE_UUID, actual.getUuid());
        assertEquals(RESOURCE_URL, actual.getUrl());
        assertEquals(THUMBNAIL_URL, actual.getThumbnail());
    }

    @Test
    void shouldMapWithoutThumbnail(@Mock( answer = Answers.RETURNS_DEEP_STUBS ) ResourceEntity entity) {
        mapper.setUrlPath(RESOURCE_PATH);
        when(entity.getUuid()).thenReturn(RESOURCE_UUID);
        when(entity.getOuterReference()).thenReturn(RESOURCE_REFERENCE);
        
        Resource actual = mapper.map(entity);
        assertEquals(RESOURCE_UUID, actual.getUuid());
        assertEquals(RESOURCE_URL, actual.getUrl());
        assertNull(actual.getThumbnail());
    }
    
    @Test
    void shouldMapWithNotConfiguredUrlPath(@Mock( answer = Answers.RETURNS_DEEP_STUBS ) ResourceEntity entity) {
        when(entity.getUuid()).thenReturn(RESOURCE_UUID);
        when(entity.getOuterReference()).thenReturn(RESOURCE_REFERENCE);
        when(entity.getThumbnail().getOuterReference()).thenReturn(THUMBNAIL_REFERENCE);

        Resource actual = mapper.map(entity);
        assertEquals(RESOURCE_UUID, actual.getUuid());
        assertEquals(RESOURCE_REFERENCE, actual.getUrl());
        assertEquals(THUMBNAIL_REFERENCE, actual.getThumbnail());
    }

}
