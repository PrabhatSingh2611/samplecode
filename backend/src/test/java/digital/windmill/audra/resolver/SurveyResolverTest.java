package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.QuestionFacade;
import digital.windmill.audra.graphql.resolver.survey.SurveyResolver;
import digital.windmill.audra.graphql.type.Question;
import digital.windmill.audra.graphql.type.Survey;
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
class SurveyResolverTest {

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private QuestionFacade facade;

    @InjectMocks
    SurveyResolver resolver;

    @Test
    void shouldGetQuestions(
            @Mock Survey input,
            @Mock Question question) {
        when(input.getId()).thenReturn(TEST_UUID);
        var pagedQuestion = createOneItemPage(question);
        when(facade.getQuestions(any(UUID.class)))
                .thenReturn(pagedQuestion);

        var result = resolver.questions(input);
        assertNotNull(result);
        assertSame(question, result.getItems().get(0));
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

}
