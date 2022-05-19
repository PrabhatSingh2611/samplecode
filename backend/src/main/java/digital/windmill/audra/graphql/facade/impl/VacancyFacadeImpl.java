package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.VacancyFacade;
import digital.windmill.audra.graphql.mapper.VacancyMapper;
import digital.windmill.audra.graphql.type.Vacancy;
import digital.windmill.audra.graphql.type.input.CreateVacancyInput;
import digital.windmill.audra.graphql.type.input.UpdateVacancyInput;
import digital.windmill.audra.graphql.type.input.VacanciesInput;
import digital.windmill.audra.service.EmployeePositionService;
import digital.windmill.audra.service.EmployeeService;
import digital.windmill.audra.service.VacancyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class VacancyFacadeImpl implements VacancyFacade {

    private VacancyService vacancyService;
    private EmployeePositionService employeePositionService;
    private EmployeeService employeeService;
    private VacancyMapper vacancyMapper;

    @Transactional(readOnly = true)
    public Vacancy findVacancyByUuid(UUID uuid) {
        var vacancyEntity = vacancyService.findVacancyByUuid(uuid);
        return vacancyMapper.mapVacancyEntityToVacancy(vacancyEntity);
    }

    @Transactional(readOnly = true)
    public Page<Vacancy> getVacancies(VacanciesInput input) {
        return vacancyService.findAllVacancies(input);
    }

    public Vacancy createVacancy(CreateVacancyInput input) {
        var employeePositionEntity = employeePositionService.findEmployeePositionByUuid(input.getPosition());
        var employeeEntity = employeeService.findEmployeeByUuid(input.getAssignTo());
        var vacancyEntity = vacancyMapper.mapInputToEntity(input, employeePositionEntity, employeeEntity);
        return vacancyMapper.mapVacancyEntityToVacancy(vacancyService.save(vacancyEntity));
    }

    public Vacancy updateVacancy(UpdateVacancyInput input) {
        var employeePositionEntity = employeePositionService.findEmployeePositionByUuid(input.getPosition());
        var employeeEntity = employeeService.findEmployeeByUuid(input.getAssignTo());
        var vacancyEntity = vacancyService.findVacancyByUuid(input.getId());
        vacancyMapper.mapToEntityWhenUpdate(vacancyEntity, input, employeePositionEntity, employeeEntity);
        return vacancyMapper.mapVacancyEntityToVacancy(vacancyService.save(vacancyEntity));
    }
}