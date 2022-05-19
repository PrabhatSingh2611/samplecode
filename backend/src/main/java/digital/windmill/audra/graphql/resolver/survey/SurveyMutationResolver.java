package digital.windmill.audra.graphql.resolver.survey;

import digital.windmill.audra.graphql.facade.SurveyFacade;
import digital.windmill.audra.graphql.type.DeleteSurveyPayload;
import digital.windmill.audra.graphql.type.SurveyPayload;
import digital.windmill.audra.graphql.type.input.CreateSurveyInput;
import digital.windmill.audra.graphql.type.input.DeleteSurveyInput;
import digital.windmill.audra.graphql.type.input.PatchSurveyInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SurveyMutationResolver implements GraphQLMutationResolver {

    private SurveyFacade surveyFacade;

    public SurveyPayload createSurvey(CreateSurveyInput input) {
        return SurveyPayload.builder()
                .survey(surveyFacade.createSurvey(input))
                .build();
    }

    public SurveyPayload patchSurvey(PatchSurveyInput input) {
        return SurveyPayload.builder()
                .survey(surveyFacade.patchSurvey(input))
                .build();
    }

    public DeleteSurveyPayload deleteSurvey(DeleteSurveyInput input) {
        return DeleteSurveyPayload.builder()
                .survey(surveyFacade.deleteSurvey(input)).
                build();
    }
}
