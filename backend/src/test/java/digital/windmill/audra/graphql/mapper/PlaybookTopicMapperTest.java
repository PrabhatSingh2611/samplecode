package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.PlaybookSectionEntity;
import digital.windmill.audra.dao.entity.PlaybookSectionTopicEntity;
import digital.windmill.audra.graphql.type.input.CreatePlaybookSectionTopicInput;
import digital.windmill.audra.graphql.type.input.PatchPlaybookSectionTopicInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class PlaybookTopicMapperTest {

    private static final UUID PLAYBOOK_SECTION_UUID = UUID.fromString("472c7549-11a9-4e60-9691-db81cc104f48");
    private static final UUID PLAYBOOK_TOPIC_UUID = UUID.fromString("0e4a7a66-7d84-4638-b6cc-60e8952e3f71");
    private static final String PLAYBOOK_TOPIC_NAME = "Topic";
    private static final String PLAYBOOK_TOPIC_BODY = "Body";

    @InjectMocks
    private PlaybookTopicMapperImpl playbookTopicMapper;

    @Test
    void shouldMapPlaybookTopicEntityToPlaybookTopic() {
        var playbookTopicEntity = new PlaybookSectionTopicEntity();
        playbookTopicEntity.setUuid(PLAYBOOK_TOPIC_UUID);
        playbookTopicEntity.setName(PLAYBOOK_TOPIC_NAME);
        playbookTopicEntity.setBody(PLAYBOOK_TOPIC_BODY);

        var actualResult = playbookTopicMapper.map(playbookTopicEntity);

        Assertions.assertEquals(PLAYBOOK_TOPIC_UUID, actualResult.getId());
        Assertions.assertEquals(PLAYBOOK_TOPIC_NAME, actualResult.getName());
        Assertions.assertEquals(PLAYBOOK_TOPIC_BODY, actualResult.getBody());
    }

    @Test
    void shouldMapCreatePlaybookSectionTopicInputToPlaybookSectionTopicEntity() {
        var createPlaybookSectionTopicInput = new CreatePlaybookSectionTopicInput();
        createPlaybookSectionTopicInput.setName(PLAYBOOK_TOPIC_NAME);

        var playbookSectionEntity = new PlaybookSectionEntity();
        playbookSectionEntity.setUuid(PLAYBOOK_SECTION_UUID);

        var actualResult = playbookTopicMapper.map(createPlaybookSectionTopicInput, playbookSectionEntity, 10);

        Assertions.assertNotNull(actualResult.getUuid());
        Assertions.assertEquals(PLAYBOOK_TOPIC_NAME, actualResult.getName());
        Assertions.assertEquals(PLAYBOOK_SECTION_UUID, actualResult.getSection().getUuid());
        Assertions.assertEquals(10, actualResult.getSort());
    }

    @Test
    void shouldMapPatchPlaybookSectionTopicInput() {
        var playbookSectionTopicEntity = new PlaybookSectionTopicEntity();
        playbookSectionTopicEntity.setName(PLAYBOOK_TOPIC_NAME);
        playbookSectionTopicEntity.setBody(PLAYBOOK_TOPIC_BODY);

        var patchPlaybookSectionTopicInput = new PatchPlaybookSectionTopicInput();
        patchPlaybookSectionTopicInput.setName("patched name");
        patchPlaybookSectionTopicInput.setBody("patched body");

        var actualResult = playbookTopicMapper.map(playbookSectionTopicEntity, patchPlaybookSectionTopicInput);

        Assertions.assertEquals("patched name", actualResult.getName());
        Assertions.assertEquals("patched body", actualResult.getBody());
    }
}