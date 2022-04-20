package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.VacancySpecification;
import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.VacancyEntity;
import digital.windmill.audra.dao.repository.VacancyRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.mapper.EmployeePositionMapper;
import digital.windmill.audra.graphql.mapper.VacancyMapper;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.Vacancy;
import digital.windmill.audra.graphql.type.input.CreateVacancyInput;
import digital.windmill.audra.graphql.type.input.UpdateVacancyInput;
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
    private EmployeePositionMapper employeePositionMapper;
    private EmployeeMapper employeeMapper;

    public Vacancy findVacancyByUuid(UUID uuid) {
        return vacancyMapper.mapVacancyEntityToVacancy(vacancyRepository.findVacancyByUuid(uuid).orElseThrow(
                        () -> new DataNotFoundException("Vacancy " + uuid + "not found.")
                )
        );
    }

    public Page<Vacancy> findAllVacancies(VacanciesInput input) {
        var spec = VacancySpecification.vacancies(input);
        return vacancyRepository.findAll(spec.getKey(), spec.getValue()).map(vacancyMapper::mapVacancyEntityToVacancy);
    }

    public Vacancy createVacancy(CreateVacancyInput input,
                                 EmployeePosition employeePosition,
                                 Employee employee) {
        var vacancyEntity = vacancyMapper
                .mapInputToEntity(
                        input,
                        employeePositionMapper.mapEmployeePositionToEmployeePositionEntity(employeePosition),
                        employeeMapper.mapEmployeeToEmployeeEntity(employee)
                        );
        return vacancyMapper.mapVacancyEntityToVacancy(vacancyRepository.save(vacancyEntity));
    }

    public Vacancy updateVacancy(UpdateVacancyInput input,
                                 EmployeePosition employeePosition,
                                 Employee employee) {
        VacancyEntity entity = vacancyRepository.findVacancyByUuid(input.getUuid())
                .orElseThrow(
                        () -> new DataNotFoundException("Vacancy not found.")
                );

        var mappedToEntity = vacancyMapper.mapToEntityWhenUpdate(entity,
                input,
                employeePositionMapper.mapEmployeePositionToEmployeePositionEntity(employeePosition),
                employeeMapper.mapEmployeeToEmployeeEntity(employee));

        return vacancyMapper.mapVacancyEntityToVacancy(vacancyRepository.save(mappedToEntity));
    }
}
