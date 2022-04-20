package digital.windmill.audra.resolver;

import digital.windmill.audra.dao.entity.enums.VacancyPriority;
import digital.windmill.audra.dao.entity.enums.VacancyStatus;
import digital.windmill.audra.graphql.facade.VacancyFacade;
import digital.windmill.audra.graphql.resolver.vacancy.VacancyMutationResolver;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.Vacancy;
import digital.windmill.audra.graphql.type.input.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VacancyMutationResolverTest {

    private static final UUID TEST_UUID = UUID.fromString("91817d8b-5851-413f-9fd5-31f64cef4692");
    private static final String DESCRIPTION = "Vacancy description";
    private static final String NAME = "Name";
    private static final String ROLE = "Admin";
    private final static ZonedDateTime DATE_TIME = ZonedDateTime.now();


    @Mock
    private VacancyFacade vacancyFacade;

    @InjectMocks
    private VacancyMutationResolver vacancyResolver;

    @Test
    void shouldCreateVacancy(@Mock CreateVacancyInput vacancyInput) {
        when(vacancyFacade.createVacancy(any(CreateVacancyInput.class))).thenReturn(createVacancy());

        var actualResult = vacancyResolver.createVacancy(vacancyInput);
        assertNotNull(actualResult);
        assertEquals(TEST_UUID, actualResult.getItem().getUuid());
        assertEquals(TEST_UUID, actualResult.getItem().getPosition().getUuid());
        assertEquals(NAME, actualResult.getItem().getPosition().getName());
        assertEquals(TEST_UUID, actualResult.getItem().getAssignTo().getUuid());
        assertEquals(NAME, actualResult.getItem().getAssignTo().getFirstName());
        assertEquals(NAME, actualResult.getItem().getAssignTo().getLastName());
        assertEquals(ROLE, actualResult.getItem().getAssignTo().getRole());
        assertEquals(DATE_TIME, actualResult.getItem().getAssignTo().getBirthday());
        assertEquals(DESCRIPTION, actualResult.getItem().getDescription());
        assertEquals(VacancyStatus.NEW, actualResult.getItem().getStatus());
        assertEquals(VacancyPriority.LOW, actualResult.getItem().getPriority());
    }

    @Test
    void shouldUpdateVacancy(@Mock UpdateVacancyInput vacancyInput) {
        when(vacancyFacade.updateVacancy(any(UpdateVacancyInput.class))).thenReturn(createVacancy());

        var actualResult = vacancyResolver.updateVacancy(vacancyInput);
        assertNotNull(actualResult);
        assertEquals(TEST_UUID, actualResult.getItem().getUuid());
        assertEquals(TEST_UUID, actualResult.getItem().getPosition().getUuid());
        assertEquals(NAME, actualResult.getItem().getPosition().getName());
        assertEquals(TEST_UUID, actualResult.getItem().getAssignTo().getUuid());
        assertEquals(NAME, actualResult.getItem().getAssignTo().getFirstName());
        assertEquals(NAME, actualResult.getItem().getAssignTo().getLastName());
        assertEquals(ROLE, actualResult.getItem().getAssignTo().getRole());
        assertEquals(DATE_TIME, actualResult.getItem().getAssignTo().getBirthday());
        assertEquals(DESCRIPTION, actualResult.getItem().getDescription());
        assertEquals(VacancyStatus.NEW, actualResult.getItem().getStatus());
        assertEquals(VacancyPriority.LOW, actualResult.getItem().getPriority());
    }


    private NodeInput createNodeInput() {
        return NodeInput
                .builder()
                .uuid(TEST_UUID)
                .build();
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
        return Location.builder().id(1L).uuid(TEST_UUID).name(NAME).build();
    }
}
