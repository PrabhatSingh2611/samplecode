package digital.windmill.audra.graphql.resolver.playbook;

import digital.windmill.audra.graphql.facade.impl.PlaybookFacade;
import digital.windmill.audra.graphql.type.Playbook;
import digital.windmill.audra.graphql.type.input.CreatePlaybookInput;
import digital.windmill.audra.graphql.type.input.DeletePlaybookInput;
import digital.windmill.audra.graphql.type.input.PatchPlaybookInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaybookMutationResolverTest {

    @Mock
    private PlaybookFacade playbookFacade;
    @InjectMocks
    PlaybookMutationResolver resolver;

    @Test
    void shouldCreatePlaybook(
            @Mock CreatePlaybookInput input,
            @Mock Playbook playbook) {
        when(playbookFacade.createPlaybook(any(CreatePlaybookInput.class)))
                .thenReturn(playbook);

        var result = resolver.createPlaybook(input);
        assertNotNull(result);
        assertSame(playbook, result.getPlaybook());
    }

    @Test
    void shouldUpdatePlaybook(
            @Mock PatchPlaybookInput input,
            @Mock Playbook playbook) {
        when(playbookFacade.patchPlaybook(any(PatchPlaybookInput.class)))
                .thenReturn(playbook);

        var result = resolver.patchPlaybook(input);
        assertNotNull(result);
        assertSame(playbook, result.getPlaybook());
    }

    @Test
    void shouldDeletePlaybook(
            @Mock DeletePlaybookInput input,
            @Mock Playbook playbook) {
        when(playbookFacade.deletePlaybook(any(DeletePlaybookInput.class)))
                .thenReturn(playbook);

        var result = resolver.deletePlaybook(input);
        assertNotNull(result);
        assertSame(playbook, result.getPlaybook());
    }
}
