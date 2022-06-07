package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.SurveyFacade;
import digital.windmill.audra.graphql.mapper.SurveyMapper;
import digital.windmill.audra.graphql.type.Survey;
import digital.windmill.audra.graphql.type.input.CreateSurveyInput;
import digital.windmill.audra.graphql.type.input.DeleteSurveyInput;
import digital.windmill.audra.graphql.type.input.PatchSurveyInput;
import digital.windmill.audra.graphql.type.input.SurveysInput;
import digital.windmill.audra.service.SurveyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SurveyFacadeImpl implements SurveyFacade {

    private SurveyService surveyService;
    private SurveyMapper surveyMapper;

    @Transactional(readOnly = true)
    public Survey findSurveyByUuid(UUID uuid) {
        return surveyMapper.mapSurveyEntityToSurvey(
                surveyService.findSurveyByUuid(uuid)
        );
    }

    @Transactional(readOnly = true)
    public Page<Survey> getSurveys(SurveysInput input) {
        return surveyService.findAllSurveys(input)
                .map(surveyMapper::mapSurveyEntityToSurvey);
    }

    @Transactional
    public Survey createSurvey(CreateSurveyInput input) {
        return surveyMapper.mapSurveyEntityToSurvey(surveyService.save(
                surveyMapper.mapCreateSurveyInputToSurveyEntity(input)
        ));
    }

    @Transactional
    public Survey patchSurvey(PatchSurveyInput input) {
        var surveyToBeUpdated = surveyService.findSurveyByUuid(input.getId());
        return surveyMapper.mapSurveyEntityToSurvey(surveyService.save(
                surveyMapper.mapPatchSurveyInputToSurveyEntity(input, surveyToBeUpdated)
        ));
    }

    @Transactional
    public Survey deleteSurvey(DeleteSurveyInput input) {
        var surveyToBeDeleted = surveyService.findSurveyByUuid(input.getId());
        return surveyMapper.mapSurveyEntityToSurvey(
                surveyService.deleteSurvey(surveyToBeDeleted)
        );
    }
}
