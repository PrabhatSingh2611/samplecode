package digital.windmill.audra.graphql.resolver.playbookTopic;

import digital.windmill.audra.graphql.facade.impl.PlaybookTopicFacade;
import digital.windmill.audra.graphql.type.PlaybookSectionTopic;
import digital.windmill.audra.graphql.type.input.PlaybookSectionTopicInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaybookTopicQueryResolverTest {

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private PlaybookTopicFacade facade;

    @InjectMocks
    PlaybookTopicQueryResolver resolver;

    @Test
    void shouldGetPlaybookSectionTopic(
            @Mock PlaybookSectionTopicInput input,
            @Mock PlaybookSectionTopic playbook) {
        when(input.getId()).thenReturn(TEST_UUID);
        when(facade.findTopicByUuid(any(UUID.class)))
                .thenReturn(playbook);

        var result = resolver.playbookSectionTopic(input);
        assertNotNull(result);
        assertSame(playbook, result.getTopic());
    }

}
