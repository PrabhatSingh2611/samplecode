package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.dao.entity.PlaybookEntity;
import digital.windmill.audra.dao.entity.PlaybookSectionEntity;
import digital.windmill.audra.graphql.mapper.PlaybookMapper;
import digital.windmill.audra.graphql.mapper.PlaybookSectionMapper;
import digital.windmill.audra.graphql.type.DeletePlaybookSectionInput;
import digital.windmill.audra.graphql.type.PatchPlaybookSectionInput;
import digital.windmill.audra.graphql.type.Playbook;
import digital.windmill.audra.graphql.type.PlaybookSection;
import digital.windmill.audra.graphql.type.ReorderPlaybookSectionInput;
import digital.windmill.audra.graphql.type.input.CreatePlaybookSectionInput;
import digital.windmill.audra.service.PlaybookSectionService;
import digital.windmill.audra.service.impl.PlaybookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaybookSectionFacadeTest {

    private static final UUID PLAYBOOK_UUID = UUID.randomUUID();
    private static final UUID PLAYBOOK_SECTION_UUID = UUID.randomUUID();
    private static final UUID PLAYBOOK_SECTION2_UUID = UUID.randomUUID();

    @InjectMocks
    private PlaybookSectionFacade playbookSectionFacade;

    @Mock
    private PlaybookSectionMapper playbookSectionMapper;
    @Mock
    private PlaybookMapper playbookMapper;

    @Mock
    private PlaybookSectionService playbookSectionService;

    @Mock
    private PlaybookService playbookService;

    @Test
    void shouldCreatePlaybookSection(@Mock(answer = Answers.RETURNS_DEEP_STUBS) CreatePlaybookSectionInput input,
                                     @Mock PlaybookEntity playbookEntity,
                                     @Mock PlaybookSectionEntity playbookSectionEntity,
                                     @Mock PlaybookSection playbookSection) {
        when(input.getPlaybook().getId()).thenReturn(PLAYBOOK_UUID);
        when(playbookService.findPlaybookByUuid(PLAYBOOK_UUID)).thenReturn(playbookEntity);
        when(playbookSectionService.getNextSortPosition(playbookEntity)).thenReturn(1);
        when(playbookSectionMapper.map(input, playbookEntity, 1)).thenReturn(playbookSectionEntity);
        when(playbookSectionService.save(playbookSectionEntity)).thenReturn(playbookSectionEntity);
        when(playbookSectionMapper.map(playbookSectionEntity)).thenReturn(playbookSection);

        var actualResult = playbookSectionFacade.createPlaybookSection(input);

        Assertions.assertEquals(playbookSection, actualResult);
    }

    @Test
    void shouldPatchPlaybookSection(@Mock PatchPlaybookSectionInput input,
                                    @Mock PlaybookSectionEntity playbookSectionEntity,
                                    @Mock PlaybookSection playbookSection) {

        when(input.getId()).thenReturn(PLAYBOOK_SECTION_UUID);
        when(playbookSectionService.findByUuid(PLAYBOOK_SECTION_UUID)).thenReturn(playbookSectionEntity);
        when(playbookSectionMapper.map(playbookSectionEntity, input)).thenReturn(playbookSectionEntity);
        when(playbookSectionService.save(playbookSectionEntity)).thenReturn(playbookSectionEntity);
        when(playbookSectionMapper.map(playbookSectionEntity)).thenReturn(playbookSection);

        var actualResult = playbookSectionFacade.patchPlaybookSection(input);

        Assertions.assertEquals(playbookSection, actualResult);
    }

    @Test
    void shouldDeletePlaybookSection(@Mock DeletePlaybookSectionInput input,
                                     @Mock PlaybookSectionEntity playbookSectionEntity,
                                     @Mock PlaybookSection playbookSection) {
        when(input.getId()).thenReturn(PLAYBOOK_SECTION_UUID);
        when(playbookSectionService.findByUuid(PLAYBOOK_SECTION_UUID)).thenReturn(playbookSectionEntity);
        when(playbookSectionMapper.map(playbookSectionEntity)).thenReturn(playbookSection);

        var actualResult = playbookSectionFacade.deletePlaybookSection(input);

        Assertions.assertEquals(playbookSection, actualResult);
        verify(playbookSectionService).delete(playbookSectionEntity);
    }

    @Test
    void shouldReorderPlaybookSection(@Mock ReorderPlaybookSectionInput input,
                                      @Mock PlaybookSectionEntity playbookSectionEntity,
                                      @Mock PlaybookSectionEntity afterPlaybookSectionEntity,
                                      @Mock PlaybookEntity playbookEntity,
                                      @Mock Playbook playbook) {
        when(input.getId()).thenReturn(PLAYBOOK_SECTION_UUID);
        when(input.getAfterId()).thenReturn(PLAYBOOK_SECTION2_UUID);
        when(playbookSectionService.findByUuid(PLAYBOOK_SECTION_UUID)).thenReturn(playbookSectionEntity);
        when(playbookSectionService.findByUuid(PLAYBOOK_SECTION2_UUID)).thenReturn(afterPlaybookSectionEntity);
        when(playbookSectionEntity.getPlaybook()).thenReturn(playbookEntity);
        when(playbookSectionEntity.getPlaybook().getUuid()).thenReturn(PLAYBOOK_UUID);
        when(playbookService.findPlaybookByUuid(PLAYBOOK_UUID)).thenReturn(playbookEntity);
        when(playbookMapper.map(playbookEntity)).thenReturn(playbook);


        var actualResult = playbookSectionFacade.reorderPlaybookSection(input);

        Assertions.assertEquals(playbook, actualResult);
        verify(playbookSectionService).reorder(playbookSectionEntity, afterPlaybookSectionEntity);
    }
}