package digital.windmill.audra.mapper;


import digital.windmill.audra.dao.entity.PlaybookEntity;
import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.graphql.mapper.PlaybookMapperImpl;
import digital.windmill.audra.graphql.mapper.ResourceMapper;
import digital.windmill.audra.graphql.type.Resource;
import digital.windmill.i18n.dao.MultiLanguageText;
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
class PlaybookMapperTest {
    private static final UUID TEST_UUID = UUID.fromString("755b97b4-bb9a-4318-bf3a-a23646e592af");
    private static final Long ID = 22L;
    private static final String TEXT = "9AMj3X";

    @Mock
    private I18nMapper i18nMapper;

    @Mock
    private ResourceMapper resourceMapper;

    @InjectMocks
    PlaybookMapperImpl mapper;

    @Test
    void shouldMap(@Mock Resource resource) {
        when(resource.getId()).thenReturn(TEST_UUID);
        when(resourceMapper.map(any(ResourceEntity.class))).thenReturn(resource);
        when(i18nMapper.get(any(MultiLanguageText.class)))
                .then(i -> i.getArgument(0, MultiLanguageText.class).get("en"));

        var result = mapper.map(createPlaybookEntity());
        assertNotNull(result);
        assertEquals(TEXT, result.getTitle());
        assertEquals(TEST_UUID, result.getId());
        assertEquals(TEST_UUID, result.getImage().getId());
    }

    private PlaybookEntity createPlaybookEntity() {
        var p = new PlaybookEntity();
        p.setId(ID);
        p.setUuid(TEST_UUID);
        p.setTitle(new MultiLanguageText() {{
            put("en", TEXT);
        }});
        p.setImage(createResourceEntity());
        return p;
    }

    private ResourceEntity createResourceEntity() {
        var r = new ResourceEntity();
        r.setUuid(TEST_UUID);
        r.setId(ID);
        r.setOuterReference(TEXT);
        return r;
    }
}
