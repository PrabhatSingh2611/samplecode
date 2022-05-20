package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.entity.VacancyEntity;
import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.dao.entity.enums.VacancyPriority;
import digital.windmill.audra.dao.entity.enums.VacancyStatus;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.mapper.EmployeePositionMapper;
import digital.windmill.audra.graphql.mapper.VacancyMapperImpl;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateVacancyInput;
import digital.windmill.audra.graphql.type.input.UpdateVacancyInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VacancyMapperTest {

    private static final UUID TEST_UUID = UUID.fromString("91817d8b-5851-413f-9fd5-31f64cef4692");
    private static final String DESCRIPTION = "Vacancy description";
    private static final String NAME = "Name";
    private final static Instant LOCALE_DATE_TIME = Instant.now();
    private final static ZonedDateTime DATE_TIME = ZonedDateTime.now();
    private static final EmployeeRole ROLE = EmployeeRole.EMPLOYEE;
    private static final Long ID = 1L;

    @Mock
    EmployeePositionMapper employeePositionMapper;

    @Mock
    EmployeeMapper employeeMapper;

    @InjectMocks
    private VacancyMapperImpl mapper;


    @Test
    void shouldMapVacancyEntityToVacancy() {
        when(employeePositionMapper.mapEmployeePositionEntityToEmployeePosition(any(EmployeePositionEntity.class)))
                .thenReturn(createPosition());
        when(employeeMapper.mapEmployeeEntityToEmployee(any(EmployeeEntity.class))).thenReturn(createEmployee());

        var result = mapper.mapVacancyEntityToVacancy(createVacancyEntity());
        assertAll(
                () -> assertEquals(TEST_UUID, result.getId()),
                () -> assertEquals(DESCRIPTION, result.getDescription()),
                () -> assertEquals(NAME, result.getPosition().getName()),
                () -> assertEquals(TEST_UUID, result.getPosition().getId()),
                () -> assertEquals(TEST_UUID, result.getAssignTo().getId()),
                () -> assertEquals(VacancyStatus.NEW, result.getStatus()),
                () -> assertEquals(VacancyPriority.LOW, result.getPriority())
        );
    }

    @Test
    void shouldMapToEntityWhenUpdate() {
        var result = mapper.mapToEntityWhenUpdate(
                createVacancyEntity(),
                updateVacancyInput(),
                createEmployeePositionEntity(),
                createEmployeeEntity());
        assertAll(
                () -> assertEquals(TEST_UUID, result.getUuid()),
                () -> assertEquals(DESCRIPTION, result.getDescription()),
                () -> assertEquals(NAME, result.getPosition().getName()),
                () -> assertEquals(TEST_UUID, result.getPosition().getUuid()),
                () -> assertEquals(TEST_UUID, result.getAssignTo().getUuid()),
                () -> assertEquals(VacancyStatus.NEW, result.getStatus()),
                () -> assertEquals(VacancyPriority.LOW, result.getPriority())
        );
    }

    @Test
    void shouldMapInputToEntity() {
        var result = mapper.mapInputToEntity(
                createVacancyInput(),
                createEmployeePositionEntity(),
                createEmployeeEntity());
        assertAll(
                () -> assertEquals(DESCRIPTION, result.getDescription()),
                () -> assertEquals(NAME, result.getPosition().getName()),
                () -> assertEquals(TEST_UUID, result.getPosition().getUuid()),
                () -> assertEquals(TEST_UUID, result.getAssignTo().getUuid()),
                () -> assertEquals(VacancyStatus.NEW, result.getStatus()),
                () -> assertEquals(VacancyPriority.LOW, result.getPriority())
        );
    }

    private CreateVacancyInput createVacancyInput() {
        return CreateVacancyInput
                .builder()
                .description(DESCRIPTION)
                .status(VacancyStatus.NEW)
                .priority(VacancyPriority.LOW)
                .build();
    }

    private UpdateVacancyInput updateVacancyInput() {
        return UpdateVacancyInput
                .builder()
                .description(DESCRIPTION)
                .status(VacancyStatus.NEW)
                .priority(VacancyPriority.LOW)
                .build();
    }

    private EmployeePosition createPosition() {
        return EmployeePosition
                .builder()
                .id(TEST_UUID)
                .name(NAME)
                .build();
    }

    private Employee createEmployee() {
        return Employee.builder()
                .id(TEST_UUID)
                .firstName(NAME)
                .lastName(NAME)
                .birthday(DATE_TIME)
                .location(createLocation())
                .role(ROLE)
                .build();
    }

    private Location createLocation() {
        return Location.builder().id(TEST_UUID).country(NAME).build();
    }

    private VacancyEntity createVacancyEntity() {
        VacancyEntity e = new VacancyEntity();
        e.setUuid(TEST_UUID);
        e.setId(ID);
        e.setDescription(DESCRIPTION);
        e.setPosition(createEmployeePositionEntity());
        e.setAssignTo(createEmployeeEntity());
        e.setStatus(VacancyStatus.NEW);
        e.setPriority(VacancyPriority.LOW);
        return e;
    }

    private EmployeePositionEntity createEmployeePositionEntity() {
        EmployeePositionEntity e = new EmployeePositionEntity();
        e.setId(ID);
        e.setUuid(TEST_UUID);
        e.setName(NAME);
        return e;
    }

    private EmployeeEntity createEmployeeEntity() {
        EmployeeEntity e = new EmployeeEntity();
        e.setUuid(TEST_UUID);
        e.setFirstName(NAME);
        e.setLastName(NAME);
        e.setBirthday(LOCALE_DATE_TIME);
        e.setId(ID);
        e.setPosition(createPositionEntity());
        e.setLocation(createLocationEntity());

        return e;

    }

    private LocationEntity createLocationEntity() {
        LocationEntity e = new LocationEntity();
        e.setId(ID);
        e.setCountry(NAME);
        return e;
    }

    private EmployeePositionEntity createPositionEntity() {
        return EmployeePositionEntity.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }
}
