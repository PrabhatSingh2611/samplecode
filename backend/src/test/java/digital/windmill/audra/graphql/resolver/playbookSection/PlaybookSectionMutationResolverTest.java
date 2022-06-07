package digital.windmill.audra.graphql.resolver.playbookSection;

import digital.windmill.audra.graphql.facade.PlaybookSectionFacade;
import digital.windmill.audra.graphql.type.DeletePlaybookSectionInput;
import digital.windmill.audra.graphql.type.PatchPlaybookSectionInput;
import digital.windmill.audra.graphql.type.Playbook;
import digital.windmill.audra.graphql.type.PlaybookSection;
import digital.windmill.audra.graphql.type.ReorderPlaybookSectionInput;
import digital.windmill.audra.graphql.type.input.CreatePlaybookSectionInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaybookSectionMutationResolverTest {

    @InjectMocks
    private PlaybookSectionMutationResolver playbookSectionMutationResolver;

    @Mock
    private PlaybookSectionFacade playbookSectionFacade;

    @Test
    void shouldCreatePlaybookSection(@Mock CreatePlaybookSectionInput input,
                                     @Mock PlaybookSection playbookSection) {
        when(playbookSectionFacade.createPlaybookSection(input)).thenReturn(playbookSection);
        var actualResult = playbookSectionMutationResolver.createPlaybookSection(input);
        assertEquals(playbookSection, actualResult.getSection());
    }

    @Test
    void shouldPatchPlaybookSection(@Mock PatchPlaybookSectionInput input,
                                    @Mock PlaybookSection playbookSection) {
        when(playbookSectionFacade.patchPlaybookSection(input)).thenReturn(playbookSection);
        var actualResult = playbookSectionMutationResolver.patchPlaybookSection(input);
        assertEquals(playbookSection, actualResult.getSection());
    }

    @Test
    void shouldDeletePlaybookSection(@Mock DeletePlaybookSectionInput input,
                                     @Mock PlaybookSection playbookSection) {
        when(playbookSectionFacade.deletePlaybookSection(input)).thenReturn(playbookSection);
        var actualResult = playbookSectionMutationResolver.deletePlaybookSection(input);
        assertEquals(playbookSection, actualResult.getSection());
    }

    @Test
    void shouldReorderPlaybookSection(@Mock ReorderPlaybookSectionInput input,
                                      @Mock Playbook playbook) {
        when(playbookSectionFacade.reorderPlaybookSection(input)).thenReturn(playbook);
        var actualResult = playbookSectionMutationResolver.reorderPlaybookSection(input);
        assertEquals(playbook, actualResult.getPlaybook());
    }
}