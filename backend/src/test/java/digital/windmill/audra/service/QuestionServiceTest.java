package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.QuestionEntity;
import digital.windmill.audra.dao.entity.SurveyEntity;
import digital.windmill.audra.dao.repository.QuestionRepository;
import digital.windmill.audra.dao.repository.SurveyRepository;
import digital.windmill.audra.service.impl.QuestionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private QuestionRepository repository;
    @Mock
    private SurveyRepository surveyRepository;

    @InjectMocks
    QuestionServiceImpl service;


    @Test
    void shouldReturnAllQuestions(@Mock QuestionEntity questionEntity,
                                  @Mock SurveyEntity surveyEntity) {
        when(surveyRepository.findSurveyByUuid(any(UUID.class))).thenReturn(Optional.of(surveyEntity));
        var questionEntityPage = createOneItemPage(questionEntity);
        when(repository.findAll(any(Specification.class), any(PageRequest.class)))
                .thenReturn(questionEntityPage);
        var result = service.findAllQuestions(TEST_UUID);
        assertNotNull(result);
        assertSame(questionEntityPage, result);
    }


    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }
}
