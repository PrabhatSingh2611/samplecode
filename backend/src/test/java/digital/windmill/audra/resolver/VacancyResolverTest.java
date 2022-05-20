package digital.windmill.audra.resolver;

import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.dao.entity.enums.VacancyPriority;
import digital.windmill.audra.dao.entity.enums.VacancyStatus;
import digital.windmill.audra.graphql.facade.VacancyFacade;
import digital.windmill.audra.graphql.resolver.vacancy.VacancyResolver;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.Vacancy;
import digital.windmill.audra.graphql.type.input.VacanciesInput;
import digital.windmill.audra.graphql.type.input.VacancyInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VacancyResolverTest {

    private static final UUID TEST_UUID = UUID.fromString("91817d8b-5851-413f-9fd5-31f64cef4692");
    private static final Long ID = 1L;
    private static final String DESCRIPTION = "Vacancy description";
    private static final String NAME = "Name";
    private static final EmployeeRole ROLE = EmployeeRole.EMPLOYEE;
    private static final ZonedDateTime DATE_TIME = ZonedDateTime.now();


    @Mock
    private VacancyFacade vacancyFacade;

    @InjectMocks
    private VacancyResolver vacancyResolver;

    @Test
    void shouldGetVacancy(@Mock VacancyInput vacancyInput) {
        when(vacancyInput.getId()).thenReturn(TEST_UUID);
        when(vacancyFacade.findVacancyByUuid(any(UUID.class))).thenReturn(createVacancy());

        var actualResult = vacancyResolver.vacancy(vacancyInput);
        assertNotNull(actualResult);
        assertEquals(TEST_UUID, actualResult.getItem().getId());
        assertEquals(TEST_UUID, actualResult.getItem().getPosition().getId());
        assertEquals(NAME, actualResult.getItem().getPosition().getName());
        assertEquals(TEST_UUID, actualResult.getItem().getAssignTo().getId());
        assertEquals(NAME, actualResult.getItem().getAssignTo().getFirstName());
        assertEquals(NAME, actualResult.getItem().getAssignTo().getLastName());
        assertEquals(ROLE, actualResult.getItem().getAssignTo().getRole());
        assertEquals(DATE_TIME, actualResult.getItem().getAssignTo().getBirthday());
        assertEquals(DESCRIPTION, actualResult.getItem().getDescription());
        assertEquals(VacancyStatus.NEW, actualResult.getItem().getStatus());
        assertEquals(VacancyPriority.LOW, actualResult.getItem().getPriority());
    }

    @Test
    void shouldGetAllVacancies(@Mock VacanciesInput vacanciesInput) {
        List<Vacancy> vacancies = List.of(createVacancy());
        var pagedResponse = new PageImpl<>(vacancies);
        when(vacancyFacade.getVacancies(any(VacanciesInput.class))).thenReturn(pagedResponse);

        var actualResult = vacancyResolver.vacancies(vacanciesInput);
        assertNotNull(actualResult);
        assertEquals(1, actualResult.getItems().size());
        assertEquals(TEST_UUID, actualResult.getItems().get(0).getId());
        assertEquals(TEST_UUID, actualResult.getItems().get(0).getPosition().getId());
        assertEquals(NAME, actualResult.getItems().get(0).getPosition().getName());
        assertEquals(TEST_UUID, actualResult.getItems().get(0).getAssignTo().getId());
        assertEquals(NAME, actualResult.getItems().get(0).getAssignTo().getFirstName());
        assertEquals(NAME, actualResult.getItems().get(0).getAssignTo().getLastName());
        assertEquals(ROLE, actualResult.getItems().get(0).getAssignTo().getRole());
        assertEquals(DATE_TIME, actualResult.getItems().get(0).getAssignTo().getBirthday());
        assertEquals(DESCRIPTION, actualResult.getItems().get(0).getDescription());
        assertEquals(VacancyStatus.NEW, actualResult.getItems().get(0).getStatus());
        assertEquals(VacancyPriority.LOW, actualResult.getItems().get(0).getPriority());
    }


    private Vacancy createVacancy() {
        return Vacancy
                .builder()
                .id(TEST_UUID)
                .position(createPosition())
                .assignTo(createEmployee())
                .description(DESCRIPTION)
                .status(VacancyStatus.NEW)
                .priority(VacancyPriority.LOW)
                .build();
    }

    private Employee createEmployee() {
        return Employee.builder()
                .id(TEST_UUID)
                .firstName(NAME)
                .lastName(NAME)
                .birthday(DATE_TIME)
                .location(createLocation())
                .position(createPosition())
                .role(ROLE)
                .build();
    }

    private EmployeePosition createPosition() {
        return EmployeePosition
                .builder()
                .id(TEST_UUID)
                .name(NAME)
                .build();
    }

    private Location createLocation() {
        return Location.builder().id(TEST_UUID).country(NAME).build();
    }
}
