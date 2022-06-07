package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.SurveyEntity;
import digital.windmill.audra.graphql.facade.impl.SurveyFacadeImpl;
import digital.windmill.audra.graphql.mapper.SurveyMapper;
import digital.windmill.audra.graphql.type.Survey;
import digital.windmill.audra.graphql.type.input.CreateSurveyInput;
import digital.windmill.audra.graphql.type.input.DeleteSurveyInput;
import digital.windmill.audra.graphql.type.input.PatchSurveyInput;
import digital.windmill.audra.graphql.type.input.SurveysInput;
import digital.windmill.audra.service.SurveyService;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SurveyFacadeTest {

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private SurveyService surveyService;
    @Mock
    private SurveyMapper surveyMapper;
    @InjectMocks
    SurveyFacadeImpl facade;

    @Test
    void shouldFindSurveyByUuid(
            @Mock SurveyEntity surveyEntity,
            @Mock Survey survey) {

        when(surveyService.findSurveyByUuid(any(UUID.class))).thenReturn(surveyEntity);
        when(surveyMapper.mapSurveyEntityToSurvey(any(SurveyEntity.class)))
                .thenReturn(survey);

        var result = facade.findSurveyByUuid(TEST_UUID);
        assertNotNull(result);
        assertSame(survey, result);
    }

    @Test
    void shouldGetSurveys(
            @Mock SurveysInput input,
            @Mock Survey survey,
            @Mock SurveyEntity surveyEntity) {

        var pagedResponse = createOneItemPage(surveyEntity);
        when(surveyService.findAllSurveys(any(SurveysInput.class)))
                .thenReturn(pagedResponse);
        when(surveyMapper.mapSurveyEntityToSurvey(any(SurveyEntity.class)))
                .thenReturn(survey);

        var result = facade.getSurveys(input);
        Assertions.assertEquals(new PageImpl<>(List.of(survey)), result);
    }

    @Test
    void shouldCreateSurvey(
            @Mock CreateSurveyInput input,
            @Mock Survey survey,
            @Mock SurveyEntity surveyEntity) {

        when(surveyMapper.mapSurveyEntityToSurvey(any(SurveyEntity.class)))
                .thenReturn(survey);
        when(surveyMapper.mapCreateSurveyInputToSurveyEntity(any(CreateSurveyInput.class)))
                .thenReturn(surveyEntity);
        when(surveyService.save(any(SurveyEntity.class)))
                .thenReturn(surveyEntity);

        var result = facade.createSurvey(input);
        assertNotNull(result);
        assertSame(survey, result);
    }

    @Test
    void shouldUpdateSurvey(
            @Mock PatchSurveyInput input,
            @Mock Survey survey,
            @Mock SurveyEntity surveyEntity) {

        when(input.getId()).thenReturn(TEST_UUID);
        when(surveyService.findSurveyByUuid(any(UUID.class))).thenReturn(surveyEntity);
        when(surveyMapper.mapSurveyEntityToSurvey(any(SurveyEntity.class)))
                .thenReturn(survey);
        when(surveyMapper.mapPatchSurveyInputToSurveyEntity(any(PatchSurveyInput.class),
                any(SurveyEntity.class)))
                .thenReturn(surveyEntity);
        when(surveyService.save(any(SurveyEntity.class)))
                .thenReturn(surveyEntity);

        var result = facade.patchSurvey(input);
        assertNotNull(result);
        assertSame(survey, result);
    }

    @Test
    void shouldDeleteSurvey(
            @Mock DeleteSurveyInput input,
            @Mock Survey survey,
            @Mock SurveyEntity surveyEntity) {

        when(input.getId()).thenReturn(TEST_UUID);
        when(surveyService.findSurveyByUuid(any(UUID.class))).thenReturn(surveyEntity);
        when(surveyMapper.mapSurveyEntityToSurvey(any(SurveyEntity.class)))
                .thenReturn(survey);
        when(surveyService.deleteSurvey(any(SurveyEntity.class)))
                .thenReturn(surveyEntity);

        var result = facade.deleteSurvey(input);
        assertNotNull(result);
        assertSame(survey, result);
    }

    private <T> Page<T> createOneItemPage(T item) {

        return new PageImpl<>(List.of(item));
    }

}
