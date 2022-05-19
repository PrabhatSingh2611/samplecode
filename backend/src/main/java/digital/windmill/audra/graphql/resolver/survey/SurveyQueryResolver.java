package digital.windmill.audra.graphql.resolver.survey;

import digital.windmill.audra.graphql.facade.SurveyFacade;
import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.Survey;
import digital.windmill.audra.graphql.type.SurveyPayload;
import digital.windmill.audra.graphql.type.input.SurveyInput;
import digital.windmill.audra.graphql.type.input.SurveysInput;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SurveyQueryResolver implements GraphQLQueryResolver {
    private SurveyFacade surveyFacade;

    public SurveyPayload survey(SurveyInput input) {
        return SurveyPayload.builder()
                .survey(surveyFacade.findSurveyByUuid(input.getId()))
                .build();
    }

    public ConnectionPayload<Survey> surveys(SurveysInput input) {
        return ConnectionUtils.buildPayload(surveyFacade.getSurveys(input));
    }
}
