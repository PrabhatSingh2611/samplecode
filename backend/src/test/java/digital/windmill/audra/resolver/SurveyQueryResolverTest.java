package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.SurveyFacade;
import digital.windmill.audra.graphql.resolver.survey.SurveyQueryResolver;
import digital.windmill.audra.graphql.type.Survey;
import digital.windmill.audra.graphql.type.input.SurveyInput;
import digital.windmill.audra.graphql.type.input.SurveysInput;
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
class SurveyQueryResolverTest {

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private SurveyFacade surveyFacade;

    @InjectMocks
    SurveyQueryResolver resolver;

    @Test
    void shouldGetSurvey(
            @Mock SurveyInput input,
            @Mock Survey survey) {
        when(input.getId()).thenReturn(TEST_UUID);
        when(surveyFacade.findSurveyByUuid(any(UUID.class)))
                .thenReturn(survey);

        var result = resolver.survey(input);
        assertNotNull(result);
        assertSame(survey, result.getSurvey());
    }

    @Test
    void shouldGetSurveys(
            @Mock SurveysInput input,
            @Mock Survey survey) {
        var pagedSurveys = createOneItemPage(survey);
        when(surveyFacade.getSurveys(any(SurveysInput.class)))
                .thenReturn(pagedSurveys);

        var result = resolver.surveys(input);
        assertNotNull(result);
        assertSame(survey, result.getItems().get(0));
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

}
