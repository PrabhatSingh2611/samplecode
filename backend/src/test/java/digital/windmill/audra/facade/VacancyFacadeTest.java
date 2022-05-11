package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.dao.entity.enums.VacancyPriority;
import digital.windmill.audra.dao.entity.enums.VacancyStatus;
import digital.windmill.audra.graphql.facade.impl.VacancyFacadeImpl;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.Location;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VacancyFacadeTest {

    private static final UUID TEST_UUID = UUID.fromString("91817d8b-5851-413f-9fd5-31f64cef4692");
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

    @InjectMocks
    private VacancyFacadeImpl vacancyFacade;


    @Test
    void shouldReturnVacancyById() {
        when(vacancyService.findVacancyByUuid(any(UUID.class))).thenReturn(createVacancy());

        var actualResult = vacancyFacade.findVacancyByUuid(TEST_UUID);

        assertNotNull(actualResult);
        assertEquals(TEST_UUID, actualResult.getUuid());
        assertEquals(TEST_UUID, actualResult.getUuid());
        assertEquals(TEST_UUID, actualResult.getPosition().getUuid());
        assertEquals(NAME, actualResult.getPosition().getName());
        assertEquals(TEST_UUID, actualResult.getAssignTo().getUuid());
        assertEquals(NAME, actualResult.getAssignTo().getFirstName());
        assertEquals(NAME, actualResult.getAssignTo().getLastName());
        assertEquals(ROLE, actualResult.getAssignTo().getRole());
        assertEquals(DATE_TIME, actualResult.getAssignTo().getBirthday());
        assertEquals(DESCRIPTION, actualResult.getDescription());
        assertEquals(VacancyStatus.NEW, actualResult.getStatus());
        assertEquals(VacancyPriority.LOW, actualResult.getPriority());
    }

    @Test
    void shouldGetAllVacancies(@Mock VacanciesInput vacanciesInput) {
        List<Vacancy> vacancies = List.of(createVacancy());
        var pagedResponse = new PageImpl(vacancies);
        when(vacancyService.findAllVacancies(any(VacanciesInput.class))).thenReturn(pagedResponse);

        var actualResult = vacancyFacade.getVacancies(vacanciesInput);
        assertNotNull(actualResult);
        assertEquals(1, actualResult.getContent().size());
        assertEquals(TEST_UUID, actualResult.getContent().get(0).getUuid());
        assertEquals(TEST_UUID, actualResult.getContent().get(0).getPosition().getUuid());
        assertEquals(NAME, actualResult.getContent().get(0).getPosition().getName());
        assertEquals(TEST_UUID, actualResult.getContent().get(0).getAssignTo().getUuid());
        assertEquals(NAME, actualResult.getContent().get(0).getAssignTo().getFirstName());
        assertEquals(NAME, actualResult.getContent().get(0).getAssignTo().getLastName());
        assertEquals(ROLE, actualResult.getContent().get(0).getAssignTo().getRole());
        assertEquals(DATE_TIME, actualResult.getContent().get(0).getAssignTo().getBirthday());
        assertEquals(DESCRIPTION, actualResult.getContent().get(0).getDescription());
        assertEquals(VacancyStatus.NEW, actualResult.getContent().get(0).getStatus());
        assertEquals(VacancyPriority.LOW, actualResult.getContent().get(0).getPriority());
    }


    @Test
    void shouldCreateVacancy(@Mock CreateVacancyInput vacancyInput) {
        when(vacancyInput.getAssignTo()).thenReturn(TEST_UUID);
        when(vacancyInput.getPosition()).thenReturn(TEST_UUID);
        when(employeePositionService.findEmployeePositionByUuid(any(UUID.class)))
                .thenReturn(createEmployeePosition());
        when(employeeService.findEmployeeByUuid(any(UUID.class))).thenReturn(createEmployeeEntity());
        when(vacancyService.createVacancy(any(CreateVacancyInput.class),
                any(EmployeePosition.class),
                any(EmployeeEntity.class)))
                .thenReturn(createVacancy());

        var actualResult = vacancyFacade.createVacancy(vacancyInput);
        assertNotNull(actualResult);
        assertEquals(TEST_UUID, actualResult.getUuid());
        assertEquals(TEST_UUID, actualResult.getPosition().getUuid());
        assertEquals(NAME, actualResult.getPosition().getName());
        assertEquals(TEST_UUID, actualResult.getAssignTo().getUuid());
        assertEquals(NAME, actualResult.getAssignTo().getFirstName());
        assertEquals(NAME, actualResult.getAssignTo().getLastName());
        assertEquals(ROLE, actualResult.getAssignTo().getRole());
        assertEquals(DATE_TIME, actualResult.getAssignTo().getBirthday());
        assertEquals(DESCRIPTION, actualResult.getDescription());
        assertEquals(VacancyStatus.NEW, actualResult.getStatus());
        assertEquals(VacancyPriority.LOW, actualResult.getPriority());
    }

    @Test
    void shouldUpdateVacancy(@Mock UpdateVacancyInput vacancyInput) {
        when(vacancyInput.getAssignTo()).thenReturn(TEST_UUID);
        when(vacancyInput.getPosition()).thenReturn(TEST_UUID);
        when(employeePositionService.findEmployeePositionByUuid(any(UUID.class)))
                .thenReturn(createEmployeePosition());
        when(employeeService.findEmployeeByUuid(any(UUID.class))).thenReturn(createEmployeeEntity());
        when(vacancyService.updateVacancy(any(UpdateVacancyInput.class),
                any(EmployeePosition.class),
                any(EmployeeEntity.class)))
                .thenReturn(createVacancy());

        var actualResult = vacancyFacade.updateVacancy(vacancyInput);
        assertNotNull(actualResult);
        assertEquals(TEST_UUID, actualResult.getUuid());
        assertEquals(TEST_UUID, actualResult.getPosition().getUuid());
        assertEquals(NAME, actualResult.getPosition().getName());
        assertEquals(TEST_UUID, actualResult.getAssignTo().getUuid());
        assertEquals(NAME, actualResult.getAssignTo().getFirstName());
        assertEquals(NAME, actualResult.getAssignTo().getLastName());
        assertEquals(ROLE, actualResult.getAssignTo().getRole());
        assertEquals(DATE_TIME, actualResult.getAssignTo().getBirthday());
        assertEquals(DESCRIPTION, actualResult.getDescription());
        assertEquals(VacancyStatus.NEW, actualResult.getStatus());
        assertEquals(VacancyPriority.LOW, actualResult.getPriority());
    }

    private Vacancy createVacancy() {
        return Vacancy
                .builder()
                .uuid(TEST_UUID)
                .position(createPosition())
                .assignTo(createEmployee())
                .description(DESCRIPTION)
                .status(VacancyStatus.NEW)
                .priority(VacancyPriority.LOW)
                .build();
    }

    private Employee createEmployee() {
        return Employee.builder()
                .uuid(TEST_UUID)
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
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }

    private Location createLocation() {
        return Location.builder().id(ID).uuid(TEST_UUID).name(NAME).build();
    }

    private EmployeePosition createEmployeePosition() {
        return EmployeePosition
                .builder()
                .id(ID)
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }

    private EmployeeEntity createEmployeeEntity() {
        EmployeeEntity e = new EmployeeEntity();
        e.setUuid(TEST_UUID);
        e.setFirstName(NAME);
        e.setLastName(NAME);
        e.setBirthday(LOCAL_DATE);
        e.setId(ID);
        e.setPosition(createPositionEntity());
        e.setLocation(createLocationEntity());
        return e;
    }

    private EmployeePositionEntity createPositionEntity() {
        EmployeePositionEntity p = new EmployeePositionEntity();
        p.setId(ID);
        p.setName(NAME);
        return p;
    }

    private LocationEntity createLocationEntity() {
        LocationEntity l = new LocationEntity();
        l.setId(ID);
        l.setName(NAME);
        return l;
    }
}
