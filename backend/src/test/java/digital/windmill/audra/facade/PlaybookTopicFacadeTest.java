package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.PlaybookSectionEntity;
import digital.windmill.audra.dao.entity.PlaybookSectionTopicEntity;
import digital.windmill.audra.graphql.facade.impl.PlaybookTopicFacade;
import digital.windmill.audra.graphql.mapper.PlaybookSectionMapper;
import digital.windmill.audra.graphql.mapper.PlaybookTopicMapper;
import digital.windmill.audra.graphql.type.PlaybookSection;
import digital.windmill.audra.graphql.type.PlaybookSectionTopic;
import digital.windmill.audra.graphql.type.input.CreatePlaybookSectionTopicInput;
import digital.windmill.audra.graphql.type.input.DeletePlaybookSectionTopicInput;
import digital.windmill.audra.graphql.type.input.PatchPlaybookSectionTopicInput;
import digital.windmill.audra.graphql.type.input.ReorderPlaybookSectionTopicInput;
import digital.windmill.audra.service.PlaybookSectionService;
import digital.windmill.audra.service.impl.PlaybookTopicService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaybookTopicFacadeTest {


    private static final UUID PLAYBOOK_SECTION_UUID = UUID.randomUUID();

    private static final UUID PLAYBOOK_TOPIC_UUID = UUID.randomUUID();

    @Mock
    private PlaybookTopicService service;
    @Mock
    private PlaybookTopicMapper mapper;
    @Mock
    private PlaybookSectionMapper sectionMapper;

    @Mock
    private PlaybookSectionService sectionService;
    @InjectMocks
    PlaybookTopicFacade facade;

    @Test
    void shouldFindPlaybookByUuid(
            @Mock PlaybookSectionTopicEntity entity,
            @Mock PlaybookSectionTopic topic) {

        when(service.findTopicByUuid(any(UUID.class))).thenReturn(entity);
        when(mapper.map(any(PlaybookSectionTopicEntity.class)))
                .thenReturn(topic);

        var result = facade.findTopicByUuid(PLAYBOOK_TOPIC_UUID);
        assertNotNull(result);
        assertSame(topic, result);
    }

    @Test
    void shouldCreatePlaybookTopic(@Mock(answer = Answers.RETURNS_DEEP_STUBS) CreatePlaybookSectionTopicInput input,
                                   @Mock PlaybookSectionEntity playbookSectionEntity,
                                   @Mock PlaybookSectionTopicEntity playbookTopicEntity,
                                   @Mock PlaybookSectionTopic playbookTopic) {

        when(input.getSection().getId()).thenReturn(PLAYBOOK_SECTION_UUID);
        when(sectionService.findByUuid(PLAYBOOK_SECTION_UUID)).thenReturn(playbookSectionEntity);
        when(service.getNextSortPosition(playbookSectionEntity)).thenReturn(1);
        when(mapper.map(input, playbookSectionEntity, 1)).thenReturn(playbookTopicEntity);
        when(service.save(playbookTopicEntity)).thenReturn(playbookTopicEntity);
        when(mapper.map(playbookTopicEntity)).thenReturn(playbookTopic);


        var actualResult = facade.createPlaybookTopic(input);

        Assertions.assertEquals(playbookTopic, actualResult);

    }

    @Test
    void shouldPatchPlaybookSection(@Mock PatchPlaybookSectionTopicInput input,
                                    @Mock PlaybookSectionTopicEntity playbookTopicEntity,
                                    @Mock PlaybookSectionTopic playbookSection) {


        when(input.getId()).thenReturn(PLAYBOOK_TOPIC_UUID);
        when(service.findTopicByUuid(PLAYBOOK_TOPIC_UUID)).thenReturn(playbookTopicEntity);
        when(mapper.map(playbookTopicEntity, input)).thenReturn(playbookTopicEntity);
        when(service.save(playbookTopicEntity)).thenReturn(playbookTopicEntity);
        when(mapper.map(playbookTopicEntity)).thenReturn(playbookSection);


        var actualResult = facade.patchPlaybookTopic(input);


        Assertions.assertEquals(playbookSection, actualResult);

    }

    @Test
    void shouldDeletePlaybookSection(@Mock DeletePlaybookSectionTopicInput input,
                                     @Mock PlaybookSectionTopicEntity playbookTopicEntity,
                                     @Mock PlaybookSectionTopic playbookSection) {

        when(input.getId()).thenReturn(PLAYBOOK_TOPIC_UUID);
        when(service.findTopicByUuid(PLAYBOOK_TOPIC_UUID)).thenReturn(playbookTopicEntity);
        when(mapper.map(playbookTopicEntity)).thenReturn(playbookSection);

        var actualResult = facade.deletePlaybookTopic(input);


        Assertions.assertEquals(playbookSection, actualResult);

        verify(service).delete(playbookTopicEntity);

    }

    @Test
    void shouldReorderTopic(@Mock ReorderPlaybookSectionTopicInput input,
                            @Mock PlaybookSectionTopicEntity playbookTopicEntity,
                            @Mock PlaybookSectionEntity playbookSectionEntity,
                            @Mock PlaybookSection playbookSection) {

        when(input.getId()).thenReturn(PLAYBOOK_TOPIC_UUID);
        when(input.getAfterId()).thenReturn(PLAYBOOK_TOPIC_UUID);
        when(input.getSectionId()).thenReturn(PLAYBOOK_SECTION_UUID);
        when(playbookTopicEntity.getSection()).thenReturn(playbookSectionEntity);
        when(service.findTopicByUuid(PLAYBOOK_TOPIC_UUID)).thenReturn(playbookTopicEntity);
        when(sectionService.findByUuid(PLAYBOOK_SECTION_UUID)).thenReturn(playbookSectionEntity);
        when(service.findTopicByUuid(PLAYBOOK_TOPIC_UUID)).thenReturn(playbookTopicEntity);
        when(sectionMapper.map(playbookSectionEntity)).thenReturn(playbookSection);

        var actualResult = facade.reorderTopic(input);

        Assertions.assertEquals(playbookSection, actualResult);

        verify(service).reorder(playbookTopicEntity, playbookTopicEntity, playbookSectionEntity);

    }
}
