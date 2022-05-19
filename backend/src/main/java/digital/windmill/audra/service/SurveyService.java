package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.SurveyEntity;
import digital.windmill.audra.graphql.type.input.SurveysInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface SurveyService {

    SurveyEntity findSurveyByUuid(UUID uuid);

    SurveyEntity save(SurveyEntity entity);

    SurveyEntity deleteSurvey(SurveyEntity entity);

    Page<SurveyEntity> findAllSurveys(SurveysInput input);
}
