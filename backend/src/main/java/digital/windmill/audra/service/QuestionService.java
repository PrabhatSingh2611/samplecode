package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.QuestionEntity;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface QuestionService {

    /**
     * This method will return a list of Questions.
     *
     * @param uuid for query result
     * @return a list of QuestionEntity including pagination
     */
    Page<QuestionEntity> findAllQuestions(UUID uuid);
}
