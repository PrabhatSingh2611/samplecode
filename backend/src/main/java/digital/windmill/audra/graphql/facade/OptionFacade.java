package digital.windmill.audra.graphql.facade;


import digital.windmill.audra.graphql.type.QuestionOption;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface OptionFacade {

    /**
     * This method will return a list of Options.
     *
     * @param input input for query result
     * @return a list of Option
     */
    Page<QuestionOption> getOptions(UUID uuid);
}
