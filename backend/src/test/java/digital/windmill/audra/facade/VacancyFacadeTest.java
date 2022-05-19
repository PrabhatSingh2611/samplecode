package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.VacancyEntity;
import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.graphql.facade.impl.VacancyFacadeImpl;
import digital.windmill.audra.graphql.mapper.VacancyMapper;
import digital.windmill.audra.graphql.type.Vacancy;
import digital.windmill.audra.graphql.type.input.CreateVacancyInput;
import digital.windmill.audra.graphql.type.input.UpdateVacancyInput;
import digital.windmill.audra.graphql.type.input.VacanciesInput;
import digital.windmill.audra.service.EmployeePositionService;
import digital.windmill.audra.service.EmployeeService;
import digital.windmill.audra.service.VacancyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VacancyFacadeTest {

    private static final UUID VACANCY_UUID = UUID.fromString("91817d8b-5851-413f-9fd5-31f64cef4692");
    private static final String DESCRIPTION = "Vacancy description";
    private static final String NAME = "Name";
    private static final EmployeeRole ROLE = EmployeeRole.EMPLOYEE;
    private final static ZonedDateTime DATE_TIME = ZonedDateTime.now();
    private static final Long ID = 1L;
    private final static Instant LOCAL_DATE = Instant.now();

    @Mock
    private VacancyService vacancyService;

    @Mock
    private EmployeePositionService employeePositionService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private VacancyMapper vacancyMapper;

    @InjectMocks
    private VacancyFacadeImpl vacancyFacade;


    @Test
    void shouldReturnVacancyById(@Mock VacancyEntity vacancyEntity,
                                 @Mock Vacancy vacancy) {
        when(vacancyService.findVacancyByUuid(VACANCY_UUID)).thenReturn(vacancyEntity);
        when(vacancyMapper.mapVacancyEntityToVacancy(vacancyEntity)).thenReturn(vacancy);

        var actualResult = vacancyFacade.findVacancyByUuid(VACANCY_UUID);

        assertEquals(vacancy, actualResult);
    }

    @Test
    void shouldGetAllVacancies(@Mock VacanciesInput vacanciesInput,
                               @Mock Vacancy vacancy) {
        var pagedResponse = new PageImpl<>(List.of(vacancy));
        when(vacancyService.findAllVacancies(any(VacanciesInput.class))).thenReturn(new PageImpl<>(List.of(vacancy)));

        var actualResult = vacancyFacade.getVacancies(vacanciesInput);

        assertEquals(pagedResponse, actualResult);
    }


    @Test
    void shouldCreateVacancy(@Mock CreateVacancyInput input,
                             @Mock EmployeePositionEntity employeePositionEntity,
                             @Mock EmployeeEntity employeeEntity,
                             @Mock VacancyEntity vacancyEntity,
                             @Mock Vacancy vacancy) {
        when(employeePositionService.findEmployeePositionByUuid(input.getPosition())).thenReturn(employeePositionEntity);
        when(employeeService.findEmployeeByUuid(input.getAssignTo())).thenReturn(employeeEntity);
        when(vacancyMapper.mapInputToEntity(input, employeePositionEntity, employeeEntity)).thenReturn(vacancyEntity);
        when(vacancyService.save(vacancyEntity)).thenReturn(vacancyEntity);
        when(vacancyMapper.mapVacancyEntityToVacancy(vacancyEntity)).thenReturn(vacancy);

        var actualResult = vacancyFacade.createVacancy(input);

        assertEquals(vacancy, actualResult);
    }

    @Test
    void shouldUpdateVacancy(@Mock UpdateVacancyInput input,
                             @Mock EmployeePositionEntity employeePositionEntity,
                             @Mock EmployeeEntity employeeEntity,
                             @Mock VacancyEntity vacancyEntity,
                             @Mock Vacancy vacancy) {
        when(employeePositionService.findEmployeePositionByUuid(input.getPosition())).thenReturn(employeePositionEntity);
        when(employeeService.findEmployeeByUuid(input.getAssignTo())).thenReturn(employeeEntity);
        when(vacancyService.findVacancyByUuid(input.getId())).thenReturn(vacancyEntity);
        when(vacancyService.save(vacancyEntity)).thenReturn(vacancyEntity);
        when(vacancyMapper.mapVacancyEntityToVacancy(vacancyEntity)).thenReturn(vacancy);

        var actualResult = vacancyFacade.updateVacancy(input);

        assertEquals(vacancy, actualResult);

        verify(vacancyMapper).mapToEntityWhenUpdate(vacancyEntity, input, employeePositionEntity, employeeEntity);
    }
}
