package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.Survey;
import digital.windmill.audra.graphql.type.input.CreateSurveyInput;
import digital.windmill.audra.graphql.type.input.DeleteSurveyInput;
import digital.windmill.audra.graphql.type.input.PatchSurveyInput;
import digital.windmill.audra.graphql.type.input.SurveysInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface SurveyFacade {

    /**
     * This method will return a Survey by a specific uuid.
     *
     * @param uuid uuid of which vacancy we should look
     * @return a specific Survey
     */
    Survey findSurveyByUuid(UUID uuid);

    /**
     * This method will return a list of Survey.
     *
     * @param input input for query result
     * @return a list of Survey
     */
    Page<Survey> getSurveys(SurveysInput input);

    /**
     * This method will create a Survey by a specific value.
     *
     * @param input input of which Survey we should create
     * @return created Survey
     */
    Survey createSurvey(CreateSurveyInput input);

    /**
     * This method will update a Survey by a specific value.
     *
     * @param input input of which Survey we should update
     * @return updated Vacancy
     */
    Survey patchSurvey(PatchSurveyInput input);

    /**
     * This method will delete a Survey by a specific value
     *
     * @param input input of which Survey we should delete
     * @return a specific deleted Survey
     */
    Survey deleteSurvey(DeleteSurveyInput input);


}
