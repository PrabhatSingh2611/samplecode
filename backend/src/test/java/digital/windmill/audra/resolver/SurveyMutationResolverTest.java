package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.SurveyFacade;
import digital.windmill.audra.graphql.resolver.survey.SurveyMutationResolver;
import digital.windmill.audra.graphql.type.Survey;
import digital.windmill.audra.graphql.type.input.CreateSurveyInput;
import digital.windmill.audra.graphql.type.input.DeleteSurveyInput;
import digital.windmill.audra.graphql.type.input.PatchSurveyInput;
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
class SurveyMutationResolverTest {

    @Mock
    private SurveyFacade surveyFacade;
    @InjectMocks
    SurveyMutationResolver resolver;

    @Test
    void shouldCreateSurvey(
            @Mock CreateSurveyInput input,
            @Mock Survey survey) {
        when(surveyFacade.createSurvey(any(CreateSurveyInput.class)))
                .thenReturn(survey);

        var result = resolver.createSurvey(input);
        assertNotNull(result);
        assertSame(survey, result.getSurvey());
    }

    @Test
    void shouldUpdateSurvey(
            @Mock PatchSurveyInput input,
            @Mock Survey survey) {
        when(surveyFacade.patchSurvey(any(PatchSurveyInput.class)))
                .thenReturn(survey);

        var result = resolver.patchSurvey(input);
        assertNotNull(result);
        assertSame(survey, result.getSurvey());
    }

    @Test
    void shouldDeleteSurvey(
            @Mock DeleteSurveyInput input,
            @Mock Survey survey) {
        when(surveyFacade.deleteSurvey(any(DeleteSurveyInput.class)))
                .thenReturn(survey);

        var result = resolver.deleteSurvey(input);
        assertNotNull(result);
        assertSame(survey, result.getSurvey());
    }
}
