package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.Vacancy;
import digital.windmill.audra.graphql.type.input.CreateVacancyInput;
import digital.windmill.audra.graphql.type.input.UpdateVacancyInput;
import digital.windmill.audra.graphql.type.input.VacanciesInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface VacancyFacade {

    /**
     * This method will return a Vacancy by a specific uuid.
     *
     * @param uuid uuid of which vacancy we should look
     * @return a specific Vacancy
     */
    Vacancy findVacancyByUuid(UUID uuid);

    /**
     * This method will return a list of Vacancies.
     *
     * @param input input for query result
     * @return a list of Vacancy
     */
    Page<Vacancy> getVacancies(VacanciesInput input);

    /**
     * This method will create a Vacancy by a specific value.
     *
     * @param input input of which vacancy we should create
     * @return created Vacancy
     */
    Vacancy createVacancy(CreateVacancyInput input);

    /**
     * This method will update a Vacancy by a specific value.
     *
     * @param input input of which vacancy we should update
     * @return updated Vacancy
     */
    Vacancy updateVacancy(UpdateVacancyInput input);
}
