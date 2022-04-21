package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.entity.VacancyEntity;
import digital.windmill.audra.dao.entity.enums.VacancyPriority;
import digital.windmill.audra.dao.entity.enums.VacancyStatus;
import digital.windmill.audra.dao.repository.VacancyRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.mapper.EmployeePositionMapper;
import digital.windmill.audra.graphql.mapper.VacancyMapper;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.Vacancy;
import digital.windmill.audra.graphql.type.input.CreateVacancyInput;
import digital.windmill.audra.graphql.type.input.UpdateVacancyInput;
import digital.windmill.audra.graphql.type.input.VacanciesInput;
import digital.windmill.audra.service.impl.VacancyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VacancyServiceTest {

    private static final UUID TEST_UUID = UUID.fromString("91817d8b-5851-413f-9fd5-31f64cef4692");
    private static final String DESCRIPTION = "Vacancy description";
    private static final String NAME = "Name";
    private static final String ROLE = "Admin";
    private final static ZonedDateTime DATE_TIME = ZonedDateTime.now();
    private final static Instant LOCALE_DATE_TIME = Instant.now();
    private static final Long ID = 1L;

    @Mock
    private VacancyRepository vacancyRepository;

    @Mock
    private VacancyMapper vacancyMapper;
    @Mock
    private EmployeePositionMapper employeePositionMapper;
    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private VacancyServiceImpl service;

    @Test
    void shouldReturnVacancyById() {

        when(vacancyRepository.findVacancyByUuid(any(UUID.class))).thenReturn(createOptionalVacancyEntity());
        when(vacancyMapper.mapVacancyEntityToVacancy(any(VacancyEntity.class))).thenReturn(createVacancy());

        var actualResult = service.findVacancyByUuid(TEST_UUID);

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
    void shouldThrowDataNotFoundWhenAssetInputIsNull() {
        Assertions.assertThrows(DataNotFoundException.class, () -> service.findVacancyByUuid(null));
    }

     @Test
    void shouldGetAllVacancies(@Mock VacanciesInput vacanciesInput) {
        when(vacancyRepository.findAll((Specification<VacancyEntity>) any(), any(PageRequest.class)))
                .thenReturn(createVacancyEntityList());
        when(vacancyMapper.mapVacancyEntityToVacancy(any(VacancyEntity.class))).thenReturn(createVacancy());
        var actualResult = service.findAllVacancies(vacanciesInput);
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

    private Page<VacancyEntity> createVacancyEntityList() {
        return new PageImpl<>(List.of(createVacancyEntity()));
    }

    @Test
    void shouldCreateVacancy(@Mock CreateVacancyInput vacancyInput,
                             @Mock EmployeePosition employeePosition,
                             @Mock Employee employee
                             ){
        when(employeePositionMapper.mapEmployeePositionToEmployeePositionEntity(any(EmployeePosition.class))).thenReturn(createEmployeePositionEntity());
        when(employeeMapper.mapEmployeeToEmployeeEntity(any(Employee.class))).thenReturn(createEmployeeEntity());
        when(vacancyMapper.mapInputToEntity(
                any(CreateVacancyInput.class),
                any(EmployeePositionEntity.class),
                any(EmployeeEntity.class)))
                .thenReturn(createVacancyEntity());

        when(vacancyRepository.save(any(VacancyEntity.class))).thenReturn(createVacancyEntity());
        when(vacancyMapper.mapVacancyEntityToVacancy(any(VacancyEntity.class))).thenReturn(createVacancy());

        var actualResult = service.createVacancy(vacancyInput, employeePosition, employee);

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
    void shouldUpdateVacancy(@Mock UpdateVacancyInput vacancyInput,
                             @Mock EmployeePosition employeePosition,
                             @Mock Employee employee
    ){
        when(vacancyInput.getUuid()).thenReturn(TEST_UUID);
        when(employeePositionMapper.mapEmployeePositionToEmployeePositionEntity(any(EmployeePosition.class))).thenReturn(createEmployeePositionEntity());
        when(employeeMapper.mapEmployeeToEmployeeEntity(any(Employee.class))).thenReturn(createEmployeeEntity());
        when(vacancyRepository.findVacancyByUuid(any(UUID.class))).thenReturn(createOptionalVacancyEntity());
        when(vacancyMapper.mapToEntityWhenUpdate(
                any(VacancyEntity.class),
                any(UpdateVacancyInput.class),
                any(EmployeePositionEntity.class),
                any(EmployeeEntity.class))).
                thenReturn(createVacancyEntity());

        when(vacancyRepository.save(any(VacancyEntity.class))).thenReturn(createVacancyEntity());
        when(vacancyMapper.mapVacancyEntityToVacancy(any(VacancyEntity.class))).thenReturn(createVacancy());

        var actualResult = service.updateVacancy(vacancyInput, employeePosition, employee);

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
    private VacancyEntity createVacancyEntity() {
        VacancyEntity e = new VacancyEntity();
        e.setUuid(TEST_UUID);
        e.setId(ID);
        e.setDescription(DESCRIPTION);
        return e;
    }
    private Optional<VacancyEntity> createOptionalVacancyEntity() {
        VacancyEntity e = new VacancyEntity();
        e.setUuid(TEST_UUID);
        e.setId(ID);
        e.setDescription(DESCRIPTION);
        return Optional.of(e);
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
