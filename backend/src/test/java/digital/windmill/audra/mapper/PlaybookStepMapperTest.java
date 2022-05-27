package digital.windmill.audra.mapper;


import digital.windmill.audra.dao.entity.PlaybookResourceEntity;
import digital.windmill.audra.dao.entity.PlaybookStepEntity;
import digital.windmill.audra.dao.entity.enums.PlaybookStepType;
import digital.windmill.audra.graphql.mapper.PlaybookResourceMapper;
import digital.windmill.audra.graphql.mapper.PlaybookStepMapperImpl;
import digital.windmill.audra.graphql.mapper.PlaybookTaskListMapper;
import digital.windmill.audra.graphql.mapper.PlaybookVideoMapper;
import digital.windmill.audra.graphql.type.PlaybookResource;
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
class PlaybookStepMapperTest {
    private static final UUID TEST_UUID = UUID.fromString("755b97b4-bb9a-4318-bf3a-a23646e592af");
    private static final Long ID = 22L;
    private static final String TEXT = "9AMj3X";

    @Mock
    private I18nMapper i18nMapper;
    @Mock
    private PlaybookTaskListMapper playbookTaskListMapper;
    @Mock
    private PlaybookVideoMapper playbookVideoMapper;
    @Mock
    private PlaybookResourceMapper playbookResourceMapper;

    @InjectMocks
    PlaybookStepMapperImpl mapper;

    @Test
    void shouldMap(@Mock PlaybookResource resource) {
        when(resource.getId()).thenReturn(TEST_UUID);
        when(playbookResourceMapper.map(any(PlaybookResourceEntity.class))).thenReturn(resource);
        when(i18nMapper.get(any(MultiLanguageText.class)))
                .then(i -> i.getArgument(0, MultiLanguageText.class).get("en"));

        var result = mapper.map(createPlaybookStepEntity());
        assertNotNull(result);
        assertEquals(TEXT, result.getTitle());
        assertEquals(TEST_UUID, result.getId());
        assertEquals(TEST_UUID, result.getResource().getId());
    }

    private PlaybookStepEntity createPlaybookStepEntity() {
        var p = new PlaybookStepEntity();
        p.setId(ID);
        p.setUuid(TEST_UUID);
        p.setTitle(new MultiLanguageText() {{
            put("en", TEXT);
        }});
        p.setType(PlaybookStepType.RESOURCE);
        p.setResource(createPlaybookResourceEntity());
        return p;
    }

    private PlaybookResourceEntity createPlaybookResourceEntity() {
        var pr = new PlaybookResourceEntity();
        pr.setId(ID);
        pr.setUuid(TEST_UUID);
        return pr;
    }
}
