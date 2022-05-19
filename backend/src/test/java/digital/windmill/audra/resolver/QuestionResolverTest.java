package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.OptionFacade;
import digital.windmill.audra.graphql.resolver.survey.QuestionResolver;
import digital.windmill.audra.graphql.type.Question;
import digital.windmill.audra.graphql.type.QuestionOption;
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
class QuestionResolverTest {

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private OptionFacade facade;

    @InjectMocks
    QuestionResolver resolver;

    @Test
    void shouldGetOptions(
            @Mock Question input,
            @Mock QuestionOption option) {
        when(input.getId()).thenReturn(TEST_UUID);
        var pagedOption = createOneItemPage(option);
        when(facade.getOptions(any(UUID.class)))
                .thenReturn(pagedOption);

        var result = resolver.options(input);
        assertNotNull(result);
        assertSame(option, result.getItems().get(0));
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

}
