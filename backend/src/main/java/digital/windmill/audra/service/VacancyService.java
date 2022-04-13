package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.graphql.type.Vacancy;
import digital.windmill.audra.graphql.type.input.CreateVacancyInput;
import digital.windmill.audra.graphql.type.input.UpdateVacancyInput;
import digital.windmill.audra.graphql.type.input.VacanciesInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface VacancyService {

    /**
     * This method will return a Vacancy by a specific uuid.
     *
     * @param uuid uuid of which vacancy we should look
     * @return a specific Vacancy
     */
    Vacancy findVacancyByUuid(UUID uuid);

    /**
     * This method will return a list of Vacancy.
     *
     * @param input input for query result
     * @return a list of Vacancy including pagination
     */
    Page<Vacancy> findAllVacancies(VacanciesInput input);

    /**
     * This method will create a Vacancy by a specific value.
     *
     * @param input                  input of which vacancy we should create
     * @param employeePositionEntity employeePositionEntity of which vacancy we should create
     * @param employeeEntity         employeeEntity of which vacancy we should create
     * @return created Vacancy
     */
    Vacancy createVacancy(CreateVacancyInput input,
                          EmployeePositionEntity employeePositionEntity,
                          EmployeeEntity employeeEntity);

    /**
     * This method will update a Vacancy by a specific value.
     *
     * @param input                  input of which vacancy we should update
     * @param employeePositionEntity employeePositionEntity of which vacancy we should update
     * @param employeeEntity         employeeEntity of which vacancy we should update
     * @return updated Vacancy
     */
    Vacancy updateVacancy(UpdateVacancyInput input,
                          EmployeePositionEntity employeePositionEntity,
                          EmployeeEntity employeeEntity);
}
