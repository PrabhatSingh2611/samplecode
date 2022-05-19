package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.Question;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface QuestionFacade {

    /**
     * This method will return a list of Questions.
     *
     * @param input input for query result
     * @return a list of Questions
     */
    Page<Question> getQuestions(UUID uuid);
}
