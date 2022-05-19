package digital.windmill.audra.graphql.resolver.survey;

import digital.windmill.audra.graphql.facade.OptionFacade;
import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.Question;
import digital.windmill.audra.graphql.type.QuestionOption;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QuestionResolver implements GraphQLResolver<Question> {

    private OptionFacade facade;

    public ConnectionPayload<QuestionOption> options(Question question) {
        return ConnectionUtils.buildPayload(facade.getOptions(question.getId()));
    }
}
