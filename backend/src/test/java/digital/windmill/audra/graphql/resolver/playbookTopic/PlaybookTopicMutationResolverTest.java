package digital.windmill.audra.graphql.resolver.playbookTopic;

import digital.windmill.audra.graphql.facade.impl.PlaybookTopicFacade;
import digital.windmill.audra.graphql.type.PlaybookSection;
import digital.windmill.audra.graphql.type.PlaybookSectionTopic;
import digital.windmill.audra.graphql.type.input.CreatePlaybookSectionTopicInput;
import digital.windmill.audra.graphql.type.input.DeletePlaybookSectionTopicInput;
import digital.windmill.audra.graphql.type.input.PatchPlaybookSectionTopicInput;
import digital.windmill.audra.graphql.type.input.ReorderPlaybookSectionTopicInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaybookTopicMutationResolverTest {

    @InjectMocks
    private PlaybookTopicMutationResolver playbookTopicMutationResolver;

    @Mock
    private PlaybookTopicFacade playbookTopicFacade;

    @Test
    void shouldCreatePlaybookSectionTopic(@Mock CreatePlaybookSectionTopicInput input,
                                          @Mock PlaybookSectionTopic playbookTopic) {
        when(playbookTopicFacade.createPlaybookTopic(input)).thenReturn(playbookTopic);
        var actualResult = playbookTopicMutationResolver.createPlaybookSectionTopic(input);
        assertEquals(playbookTopic, actualResult.getTopic());
    }

    @Test
    void shouldPatchPlaybookSectionTopic(@Mock PatchPlaybookSectionTopicInput input,
                                         @Mock PlaybookSectionTopic playbookTopic) {
        when(playbookTopicFacade.patchPlaybookTopic(input)).thenReturn(playbookTopic);
        var actualResult = playbookTopicMutationResolver.patchPlaybookSectionTopic(input);
        assertEquals(playbookTopic, actualResult.getTopic());
    }

    @Test
    void shouldDeletePlaybookSectionTopic(@Mock DeletePlaybookSectionTopicInput input,
                                          @Mock PlaybookSectionTopic playbookSectionTopic) {
        when(playbookTopicFacade.deletePlaybookTopic(input)).thenReturn(playbookSectionTopic);
        var actualResult = playbookTopicMutationResolver.deletePlaybookSectionTopic(input);
        assertEquals(playbookSectionTopic, actualResult.getTopic());
    }

    @Test
    void shouldReorderPlaybookSectionTopic(@Mock ReorderPlaybookSectionTopicInput input,
                                           @Mock PlaybookSection playbookSection) {
        when(playbookTopicFacade.reorderTopic(input)).thenReturn(playbookSection);
        var actualResult = playbookTopicMutationResolver.reorderPlaybookSectionTopic(input);
        assertEquals(playbookSection, actualResult.getSection());
    }
}