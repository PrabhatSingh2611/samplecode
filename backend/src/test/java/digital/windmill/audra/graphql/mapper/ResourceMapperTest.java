package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.graphql.type.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResourceMapperTest {

    private static final UUID RESOURCE_UUID = UUID.randomUUID();
    private static final String RESOURCE_EXTENSION = "jpg";
    private static final UUID RESOURCE_THUMBNAIL_UUID = UUID.randomUUID();
    private static final String RESOURCE_PATH = "http://resource_path";
    private static final String RESOURCE_URL = RESOURCE_PATH + "/" + RESOURCE_UUID;
    private static final String THUMBNAIL_URL = RESOURCE_PATH + "/" + RESOURCE_THUMBNAIL_UUID;

    private ResourceMapper mapper = new ResourceMapperImpl();

    @Test
    void shouldMap(@Mock(answer = Answers.RETURNS_DEEP_STUBS) ResourceEntity entity) {
        mapper.setUrlPath(RESOURCE_PATH);
        when(entity.getUuid()).thenReturn(RESOURCE_UUID);
        when(entity.getThumbnail().getUuid()).thenReturn(RESOURCE_THUMBNAIL_UUID);

        Resource actual = mapper.map(entity);
        assertEquals(RESOURCE_UUID, actual.getId());
        assertEquals(RESOURCE_URL, actual.getUrl());
        assertEquals(THUMBNAIL_URL, actual.getThumbnail());
    }

    @Test
    void shouldMapWithoutThumbnail(@Mock(answer = Answers.RETURNS_DEEP_STUBS) ResourceEntity entity) {
        mapper.setUrlPath(RESOURCE_PATH);
        when(entity.getUuid()).thenReturn(RESOURCE_UUID);

        Resource actual = mapper.map(entity);
        assertEquals(RESOURCE_UUID, actual.getId());
        assertEquals(RESOURCE_URL, actual.getUrl());
        assertNull(actual.getThumbnail());
    }

    @Test
    void shouldMapWithNotConfiguredUrlPath(@Mock(answer = Answers.RETURNS_DEEP_STUBS) ResourceEntity entity) {
        when(entity.getUuid()).thenReturn(RESOURCE_UUID);
        when(entity.getThumbnail().getUuid()).thenReturn(RESOURCE_THUMBNAIL_UUID);

        Resource actual = mapper.map(entity);
        assertEquals(RESOURCE_UUID, actual.getId());
        assertEquals(RESOURCE_UUID.toString(), actual.getUrl());
        assertEquals(RESOURCE_THUMBNAIL_UUID.toString(), actual.getThumbnail());
    }

}
