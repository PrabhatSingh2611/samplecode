package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.SurveyEntity;
import digital.windmill.audra.dao.repository.SurveyRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.input.SurveysInput;
import digital.windmill.audra.service.impl.SurveyServiceImpl;
import org.junit.jupiter.api.Assertions;
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
class SurveyServiceTest {

    private static final UUID TEST_UUID = UUID.randomUUID();
    @Mock
    private SurveyRepository surveyRepository;

    @InjectMocks
    SurveyServiceImpl service;

    @Test
    void shouldReturnSurveyByUuid(@Mock SurveyEntity surveyEntity) {
        when(surveyRepository.findSurveyByUuid(any(UUID.class)))
                .thenReturn(Optional.ofNullable(surveyEntity));

        var result = service.findSurveyByUuid(TEST_UUID);
        assertNotNull(result);
        assertSame(surveyEntity, result);
    }

    @Test
    void shouldThrowDataNotFound() {
        Assertions.assertThrows(DataNotFoundException.class, () -> service.findSurveyByUuid(TEST_UUID));
    }

    @Test
    void shouldReturnAllSurveys(@Mock SurveysInput input, @Mock SurveyEntity surveyEntity) {
        var surveyEntityPage = createOneItemPage(surveyEntity);
        when(surveyRepository.findAll(any(Specification.class), any(PageRequest.class)))
                .thenReturn(surveyEntityPage);
        var result = service.findAllSurveys(input);
        assertNotNull(result);
        assertSame(surveyEntityPage, result);
    }


    @Test
    void shouldSaveOrUpdateSurvey(@Mock SurveyEntity surveyEntity) {
        when(surveyRepository.save(any(SurveyEntity.class))).thenReturn(surveyEntity);

        var result = service.save(surveyEntity);
        assertNotNull(result);
        assertSame(surveyEntity, result);
    }

    @Test
    void shouldDeleteSurvey(@Mock SurveyEntity surveyEntity) {
        var result = service.deleteSurvey(surveyEntity);
        assertNotNull(result);
        assertSame(surveyEntity, result);
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }
}
