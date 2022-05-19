package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.VacancyEntity;
import digital.windmill.audra.graphql.type.Vacancy;
import digital.windmill.audra.graphql.type.input.VacanciesInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface VacancyService {

    VacancyEntity findVacancyByUuid(UUID uuid);

    Page<Vacancy> findAllVacancies(VacanciesInput input);

    VacancyEntity save(VacancyEntity vacancyEntity);
}
