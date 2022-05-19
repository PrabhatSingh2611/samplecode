package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.QuestionFacade;
import digital.windmill.audra.graphql.mapper.QuestionMapper;
import digital.windmill.audra.graphql.type.Question;
import digital.windmill.audra.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class QuestionFacadeImpl implements QuestionFacade {

    private QuestionService service;
    private QuestionMapper questionMapper;

    @Transactional(readOnly = true)
    public Page<Question> getQuestions(UUID uuid) {
        return service.findAllQuestions(uuid)
                .map(questionMapper::mapQuestionEntityToQuestion);
    }
}
