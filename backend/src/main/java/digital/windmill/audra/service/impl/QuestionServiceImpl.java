package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.QuestionSpecification;
import digital.windmill.audra.dao.entity.QuestionEntity;
import digital.windmill.audra.dao.repository.QuestionRepository;
import digital.windmill.audra.dao.repository.SurveyRepository;
import digital.windmill.audra.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private SurveyRepository surveyRepository;
    private QuestionRepository questionRepository;


    @Override
    public Page<QuestionEntity> findAllQuestions(UUID uuid) {
        var surveyEntity = surveyRepository.findSurveyByUuid(uuid);
        var specification = QuestionSpecification.byQuestions(surveyEntity.get().getQuestions());
        var pageable = PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
        return questionRepository.findAll(specification, pageable);
    }

}
