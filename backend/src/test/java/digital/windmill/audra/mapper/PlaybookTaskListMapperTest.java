package digital.windmill.audra.mapper;


import digital.windmill.audra.dao.entity.PlaybookTaskEntity;
import digital.windmill.audra.dao.entity.PlaybookTaskListEntity;
import digital.windmill.audra.graphql.mapper.PlaybookTaskListMapperImpl;
import digital.windmill.audra.graphql.mapper.PlaybookTaskMapper;
import digital.windmill.i18n.dao.MultiLanguageText;
import digital.windmill.i18n.mapper.I18nMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PlaybookTaskListMapperTest {
    private static final UUID TEST_UUID = UUID.fromString("755b97b4-bb9a-4318-bf3a-a23646e592af");
    private static final Long ID = 22L;
    private static final String TEXT = "9AMj3X";

    @Mock
    private I18nMapper i18nMapper;

    @Mock
    private PlaybookTaskMapper playbookTaskMapper;

    @InjectMocks
    PlaybookTaskListMapperImpl mapper;

    @Test
    void shouldMap() {
        var result = mapper.map(createPlaybookTaskListEntity());
        assertNotNull(result);
        assertEquals(TEST_UUID, result.getId());
        assertEquals(1, result.getTasks().size());
    }

    private PlaybookTaskListEntity createPlaybookTaskListEntity() {
        var p = new PlaybookTaskListEntity();
        p.setId(ID);
        p.setUuid(TEST_UUID);
        p.setTasks(List.of(createPlaybookTaskEntity()));
        return p;
    }

    private PlaybookTaskEntity createPlaybookTaskEntity() {
        var pt = new PlaybookTaskEntity();
        pt.setId(ID);
        pt.setUuid(TEST_UUID);
        pt.setTitle(new MultiLanguageText() {{
            put("en", TEXT);
        }});
        return pt;
    }

}
