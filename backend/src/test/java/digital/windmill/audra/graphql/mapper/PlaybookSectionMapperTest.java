package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.PlaybookEntity;
import digital.windmill.audra.dao.entity.PlaybookSectionEntity;
import digital.windmill.audra.graphql.type.PatchPlaybookSectionInput;
import digital.windmill.audra.graphql.type.input.CreatePlaybookSectionInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class PlaybookSectionMapperTest {

    private static final UUID PLAYBOOK_UUID = UUID.fromString("472c7549-11a9-4e60-9691-db81cc104f48");
    private static final UUID PLAYBOOK_SECTION_UUID = UUID.fromString("0e4a7a66-7d84-4638-b6cc-60e8952e3f71");
    private static final String PLAYBOOK_SECTION_NAME = "Section";

    @InjectMocks
    private PlaybookSectionMapperImpl playbookSectionMapper;

    @Test
    void shouldMapPlaybookSectionEntityToPlaybookSection() {
        var playbookSectionEntity = new PlaybookSectionEntity();
        playbookSectionEntity.setUuid(PLAYBOOK_SECTION_UUID);
        playbookSectionEntity.setName(PLAYBOOK_SECTION_NAME);

        var actualResult = playbookSectionMapper.map(playbookSectionEntity);

        Assertions.assertEquals(PLAYBOOK_SECTION_UUID, actualResult.getId());
        Assertions.assertEquals(PLAYBOOK_SECTION_NAME, actualResult.getName());
    }

    @Test
    void shouldMapCreatePlaybookSectionInputToPlaybookSectionEntity() {
        var createPlaybookSectionInput = new CreatePlaybookSectionInput();
        createPlaybookSectionInput.setName(PLAYBOOK_SECTION_NAME);

        var playbookEntity = new PlaybookEntity();
        playbookEntity.setUuid(PLAYBOOK_UUID);

        var actualResult = playbookSectionMapper.map(createPlaybookSectionInput, playbookEntity, 10);

        Assertions.assertNotNull(actualResult.getUuid());
        Assertions.assertEquals(PLAYBOOK_SECTION_NAME, actualResult.getName());
        Assertions.assertEquals(PLAYBOOK_UUID, actualResult.getPlaybook().getUuid());
        Assertions.assertEquals(10, actualResult.getSort());
    }

    @Test
    void shouldMapPatchPlaybookSectionInput() {
        var playbookSectionEntity = new PlaybookSectionEntity();
        playbookSectionEntity.setName(PLAYBOOK_SECTION_NAME);

        var patchPlaybookSectionInput = new PatchPlaybookSectionInput();
        patchPlaybookSectionInput.setName("patched name");

        var actualResult = playbookSectionMapper.map(playbookSectionEntity, patchPlaybookSectionInput);

        Assertions.assertEquals("patched name", actualResult.getName());
    }
}