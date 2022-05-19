package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.SurveySpecification;
import digital.windmill.audra.dao.entity.SurveyEntity;
import digital.windmill.audra.dao.repository.SurveyRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.input.PageInput;
import digital.windmill.audra.graphql.type.input.SurveysInput;
import digital.windmill.audra.service.SurveyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class SurveyServiceImpl implements SurveyService {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private SurveyRepository surveyRepository;

    @Override
    public SurveyEntity findSurveyByUuid(UUID uuid) {
        return surveyRepository.findSurveyByUuid(uuid).orElseThrow(
                () -> new DataNotFoundException("Survey not available for given UUID : " + uuid)
        );
    }

    @Override
    public Page<SurveyEntity> findAllSurveys(SurveysInput input) {
        var specification = SurveySpecification.bySurveysInput(input.getWhere());
        var itemsPerPage = Optional.of(input).map(SurveysInput::getPagination).
                map(PageInput::getItemsPerPage).orElse(DEFAULT_PAGE_SIZE);
        var pageNumber = Optional.of(input).map(SurveysInput::getPagination).
                map(PageInput::getPageNumber).orElse(DEFAULT_PAGE_NUMBER);
        var pageable = PageRequest.of(pageNumber, itemsPerPage);
        return surveyRepository.findAll(specification, pageable);
    }

    @Override
    public SurveyEntity save(SurveyEntity entity) {
        return surveyRepository.save(entity);
    }


    @Override
    public SurveyEntity deleteSurvey(SurveyEntity entity) {
        surveyRepository.delete(entity);
        return entity;
    }
}
