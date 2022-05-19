package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.QuestionEntity;
import digital.windmill.audra.graphql.facade.impl.QuestionFacadeImpl;
import digital.windmill.audra.graphql.mapper.QuestionMapper;
import digital.windmill.audra.graphql.type.Question;
import digital.windmill.audra.service.QuestionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionFacadeTest {

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private QuestionService surveyService;
    @Mock
    private QuestionMapper questionMapper;
    @InjectMocks
    QuestionFacadeImpl facade;


    @Test
    void shouldGetQuestions(
            @Mock Question question,
            @Mock QuestionEntity questionEntity) {

        var pagedResponse = createOneItemPage(questionEntity);
        when(surveyService.findAllQuestions(any(UUID.class)))
                .thenReturn(pagedResponse);
        when(questionMapper.mapQuestionEntityToQuestion(any(QuestionEntity.class)))
                .thenReturn(question);

        var result = facade.getQuestions(TEST_UUID);
        Assertions.assertEquals(new PageImpl<>(List.of(question)), result);
    }

    private <T> Page<T> createOneItemPage(T item) {

        return new PageImpl<>(List.of(item));
    }

}
