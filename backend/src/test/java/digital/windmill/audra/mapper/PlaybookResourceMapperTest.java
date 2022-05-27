package digital.windmill.audra.mapper;


import digital.windmill.audra.dao.entity.PlaybookResourceEntity;
import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.graphql.mapper.PlaybookResourceMapperImpl;
import digital.windmill.audra.graphql.mapper.ResourceMapper;
import digital.windmill.audra.graphql.type.Resource;
import digital.windmill.i18n.mapper.I18nMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaybookResourceMapperTest {
    private static final UUID TEST_UUID = UUID.fromString("755b97b4-bb9a-4318-bf3a-a23646e592af");
    private static final Long ID = 22L;
    private static final String TEXT = "9AMj3X";

    @Mock
    private I18nMapper i18nMapper;
    @Mock
    private ResourceMapper resourceMapper;

    @InjectMocks
    PlaybookResourceMapperImpl mapper;

    @Test
    void shouldMap(@Mock Resource resource) {
        when(resourceMapper.map(any(ResourceEntity.class))).thenReturn(resource);
        when(resource.getId()).thenReturn(TEST_UUID);
        var result = mapper.map(createPlaybookResourceEntity());
        assertNotNull(result);
        assertEquals(TEST_UUID, result.getId());
        assertEquals(TEST_UUID, result.getResource().getId());
    }

    private PlaybookResourceEntity createPlaybookResourceEntity() {
        var p = new PlaybookResourceEntity();
        p.setId(ID);
        p.setUuid(TEST_UUID);
        p.setResource(createResourceEntity());
        return p;
    }

    private ResourceEntity createResourceEntity() {
        var r = new ResourceEntity();
        r.setId(ID);
        r.setUuid(TEST_UUID);
        r.setOuterReference(TEXT);
        return r;
    }

}
