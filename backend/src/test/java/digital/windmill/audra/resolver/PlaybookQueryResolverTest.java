package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.impl.PlaybookFacade;
import digital.windmill.audra.graphql.resolver.playbook.PlaybookResolverQuery;
import digital.windmill.audra.graphql.type.Playbook;
import digital.windmill.audra.graphql.type.input.PlaybookInput;
import digital.windmill.audra.graphql.type.input.PlaybooksInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaybookQueryResolverTest {

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private PlaybookFacade playbookFacade;

    @InjectMocks
    PlaybookResolverQuery resolver;

    @Test
    void shouldGetPlaybook(
            @Mock PlaybookInput input,
            @Mock Playbook playbook) {
        when(input.getId()).thenReturn(TEST_UUID);
        when(playbookFacade.findPlaybookByUuid(any(UUID.class)))
                .thenReturn(playbook);

        var result = resolver.playbook(input);
        assertNotNull(result);
        assertSame(playbook, result.getPlaybook());
    }

    @Test
    void shouldGetPlaybooks(
            @Mock PlaybooksInput input,
            @Mock Playbook playbook) {
        var pagedPlaybooks = createOneItemPage(playbook);
        when(playbookFacade.getPlaybooks(any(PlaybooksInput.class)))
                .thenReturn(pagedPlaybooks);

        var result = resolver.playbooks(input);
        assertNotNull(result);
        assertSame(playbook, result.getItems().get(0));
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

}
