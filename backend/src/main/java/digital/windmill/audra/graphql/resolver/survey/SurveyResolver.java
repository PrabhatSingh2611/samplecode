package digital.windmill.audra.graphql.resolver.survey;

import digital.windmill.audra.graphql.facade.QuestionFacade;
import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.Question;
import digital.windmill.audra.graphql.type.Survey;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SurveyResolver implements GraphQLResolver<Survey> {

    private QuestionFacade facade;

    public ConnectionPayload<Question> questions(Survey survey) {
        return ConnectionUtils.buildPayload(facade.getQuestions(survey.getId()));
    }
}
