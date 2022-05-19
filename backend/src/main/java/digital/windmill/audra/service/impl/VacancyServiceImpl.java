package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.VacancySpecification;
import digital.windmill.audra.dao.entity.VacancyEntity;
import digital.windmill.audra.dao.repository.VacancyRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.VacancyMapper;
import digital.windmill.audra.graphql.type.Vacancy;
import digital.windmill.audra.graphql.type.input.VacanciesInput;
import digital.windmill.audra.service.VacancyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private VacancyRepository vacancyRepository;
    private VacancyMapper vacancyMapper;

    public VacancyEntity findVacancyByUuid(UUID uuid) {
        return vacancyRepository.findVacancyByUuid(uuid)
                .orElseThrow(() -> new DataNotFoundException("Vacancy " + uuid + "not found."));
    }

    public Page<Vacancy> findAllVacancies(VacanciesInput input) {
        var spec = VacancySpecification.vacancies(input);
        return vacancyRepository.findAll(spec.getKey(), spec.getValue()).map(vacancyMapper::mapVacancyEntityToVacancy);
    }

    @Override
    public VacancyEntity save(VacancyEntity vacancyEntity) {
        return vacancyRepository.save(vacancyEntity);
    }
}
