package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.CandidateEntity;
import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.dao.entity.VacancyEntity;
import digital.windmill.audra.dao.entity.enums.CandidateStatus;
import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.dao.entity.enums.VacancyPriority;
import digital.windmill.audra.dao.entity.enums.VacancyStatus;
import digital.windmill.audra.graphql.mapper.CandidateMapperImpl;
import digital.windmill.audra.graphql.mapper.VacancyMapper;
import digital.windmill.audra.graphql.type.Vacancy;
import digital.windmill.audra.graphql.type.input.CreateCandidateInput;
import digital.windmill.audra.graphql.type.input.PatchCandidateInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidateMapperTest {

    private static final Long ID = 1L;
    private static final UUID TEST_UUID = UUID.randomUUID();
    private static final UUID VACANCY_UUID = UUID.randomUUID();
    private static final UUID EMPLOYEE_UUID = UUID.randomUUID();
    private static final String FIRST_NAME = "FirstName";
    private static final String LAST_NAME = "LastName";
    private static final CandidateStatus STATUS = CandidateStatus.NEW;
    private static final String LINKEDIN = "xfjsbskusaS";
    private static final String DESCRIPTION = "ksjhdbwny";

    private static final EmployeeRole EMPLOYEE_ROLE = EmployeeRole.EMPLOYEE;
    private static final ZonedDateTime ZONE_DATE_TIME = ZonedDateTime.now();
    private static final Instant INSTANT_LOCAL_DATE = ZONE_DATE_TIME.toInstant();


    @Mock
    private VacancyMapper vacancyMapper;

    @InjectMocks
    CandidateMapperImpl candidateMapper;

    @Test
    void shouldMapCandidateEntityToCandidate() {

        when(vacancyMapper.mapVacancyEntityToVacancy(any(VacancyEntity.class)))
                .thenReturn(createVacancyPojo());

        var result = candidateMapper.mapCandidateEntityToCandidate(createCandidateEntity());

        assertAll(
                () -> assertEquals(FIRST_NAME, result.getFirstName()),
                () -> assertEquals(LAST_NAME, result.getLastName()),
                () -> assertEquals(STATUS, result.getStatus()),
                () -> assertEquals(LINKEDIN, result.getLinkedIn()),
                () -> assertEquals(VACANCY_UUID, result.getVacancy().getId())
        );
    }

    @Test
    void shouldMapCreateCandidateInputToCandidateEntity() {

        var result = candidateMapper.mapCreateCandidateInputToCandidateEntity(
                testCreateCandidateInput(), createVacancy(), createListResourceEntity());

        assertAll(
                () -> assertEquals(FIRST_NAME, result.getFirstName()),
                () -> assertEquals(LAST_NAME, result.getLastName()),
                () -> assertEquals(STATUS, result.getStatus()),
                () -> assertEquals(LINKEDIN, result.getLinkedIn()),
                () -> assertEquals(VACANCY_UUID, result.getVacancy().getUuid())
        );
    }


    @Test
    void shouldMapPatchCandidateInputToCandidateEntity() {

        var result = candidateMapper.mapPatchCandidateInputToCandidateEntity(
                createCandidateEntity(),
                createUpdateCandidateInput(),
                createVacancy(),
                createListResourceEntity()
        );

        assertAll(
                () -> assertEquals(FIRST_NAME, result.getFirstName()),
                () -> assertEquals(LAST_NAME, result.getLastName()),
                () -> assertEquals(STATUS, result.getStatus()),
                () -> assertEquals(LINKEDIN, result.getLinkedIn()),
                () -> assertEquals(VACANCY_UUID, result.getVacancy().getUuid())
        );
    }

    private CandidateEntity createCandidateEntity() {
        CandidateEntity entity = new CandidateEntity();
        entity.setId(ID);
        entity.setUuid(TEST_UUID);
        entity.setFirstName(FIRST_NAME);
        entity.setLastName(LAST_NAME);
        entity.setLinkedIn(LINKEDIN);
        entity.setVacancy(createVacancy());
        entity.setStatus(STATUS);
        return entity;
    }

    private VacancyEntity createVacancy() {
        VacancyEntity e = new VacancyEntity();
        e.setUuid(VACANCY_UUID);
        e.setId(ID);
        e.setDescription(DESCRIPTION);
        e.setAssignTo(createEmployeeEntity());
        e.setPosition(createEmployeePositionEntity());
        e.setStatus(VacancyStatus.NEW);
        e.setPriority(VacancyPriority.LOW);
        return e;
    }

    private Vacancy createVacancyPojo() {
        Vacancy e = new Vacancy();
        e.setId(VACANCY_UUID);
        e.setDescription(DESCRIPTION);
        e.setStatus(VacancyStatus.NEW);
        e.setPriority(VacancyPriority.LOW);
        return e;
    }

    private EmployeeEntity createEmployeeEntity() {
        EmployeeEntity e = new EmployeeEntity();
        e.setId(ID);
        e.setUuid(EMPLOYEE_UUID);
        e.setFirstName(FIRST_NAME);
        e.setLastName(LAST_NAME);
        e.setBirthday(INSTANT_LOCAL_DATE);
        e.setRole(EMPLOYEE_ROLE);
        return e;
    }

    private EmployeePositionEntity createEmployeePositionEntity() {
        EmployeePositionEntity e = new EmployeePositionEntity();
        e.setId(ID);
        e.setUuid(TEST_UUID);
        e.setName(FIRST_NAME);
        return e;
    }

    private CreateCandidateInput testCreateCandidateInput() {
        return CreateCandidateInput
                .builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .linkedIn(LINKEDIN)
                .status(STATUS)
                .vacancy(TEST_UUID)
                .build();
    }

    private PatchCandidateInput createUpdateCandidateInput() {
        return PatchCandidateInput
                .builder()
                .id(TEST_UUID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .linkedIn(LINKEDIN)
                .status(STATUS)
                .build();
    }

    private List<ResourceEntity> createListResourceEntity() {
        ResourceEntity entity = new ResourceEntity();
        entity.setId(ID);
        entity.setUuid(TEST_UUID);
        List<ResourceEntity> entities = new ArrayList<>();
        entities.add(entity);
        return entities;

    }


}
