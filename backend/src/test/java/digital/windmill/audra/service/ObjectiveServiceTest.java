package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.ObjectiveEntity;
import digital.windmill.audra.dao.entity.enums.ObjectiveStatus;
import digital.windmill.audra.dao.repository.ObjectiveRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.input.ObjectivesInput;
import digital.windmill.audra.service.impl.ObjectiveServiceImpl;
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
public class ObjectiveServiceTest {
    private static final Long ID = 1L;
    private static final UUID TEST_UUID = UUID.randomUUID();
    private static final String NAME = "Name";
    private static final String COMMENT = "schema,etc";
    private static final String DESCRIPTION = "Description";
    private static final ObjectiveStatus STATUS = ObjectiveStatus.NEW;
    private static final ZonedDateTime ZONE_DATE_TIME = ZonedDateTime.now();
    private static final Instant INSTANT_LOCAL_DATE = ZONE_DATE_TIME.toInstant();

    @Mock
    private ObjectiveRepository objectiveRepository;
    @InjectMocks
    ObjectiveServiceImpl objectiveService;

    @Test
    void shouldCreateObjective(@Mock ObjectiveEntity objectiveEntity) {
        when(objectiveRepository.save(any(ObjectiveEntity.class))).thenReturn(createObjectiveEntity());

        var result = objectiveService.createObjective(objectiveEntity);

        assertNotNull(result);
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(COMMENT, result.getComments());
        assertEquals(TEST_UUID, result.getEmployee().getUuid());
        assertEquals(STATUS, result.getStatus());
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(INSTANT_LOCAL_DATE, result.getDueToDate());
        assertEquals(INSTANT_LOCAL_DATE, result.getEmployee().getBirthday());
    }

    @Test
    void shouldUpdateObjective(@Mock ObjectiveEntity objectiveEntity) {
        when(objectiveRepository.save(any(ObjectiveEntity.class))).thenReturn(createObjectiveEntity());

        var result = objectiveService.updateObjective(objectiveEntity);

        assertNotNull(result);
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(COMMENT, result.getComments());
        assertEquals(TEST_UUID, result.getEmployee().getUuid());
        assertEquals(STATUS, result.getStatus());
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(INSTANT_LOCAL_DATE, result.getDueToDate());
        assertEquals(INSTANT_LOCAL_DATE, result.getEmployee().getBirthday());
    }

    @Test
    void shouldDeleteObjective() {
        var result = objectiveService.deleteObjective(createObjectiveEntity());

        assertNotNull(result);
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(COMMENT, result.getComments());
        assertEquals(TEST_UUID, result.getEmployee().getUuid());
        assertEquals(STATUS, result.getStatus());
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(INSTANT_LOCAL_DATE, result.getDueToDate());
        assertEquals(INSTANT_LOCAL_DATE, result.getEmployee().getBirthday());
    }

    @Test
    void shouldReturnObjectiveByUuid() {
        when(objectiveRepository.findObjectiveByUuid(any(UUID.class))).thenReturn(Optional.ofNullable(createObjectiveEntity()));

        var result = objectiveService.findObjectiveByUuid(TEST_UUID);

        assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getUuid());
    }

    @Test
    void shouldThrowDataNotFoundWhenUuidIsNull() {
        when(objectiveRepository.findObjectiveByUuid(any(UUID.class)))
                .thenThrow(new DataNotFoundException("Objective not found for :" + TEST_UUID));
        Assertions.assertThrows(DataNotFoundException.class, () -> objectiveService.findObjectiveByUuid(TEST_UUID));
    }

    @Test
    void shouldReturnAllObjective(@Mock ObjectivesInput input) {

        when(objectiveRepository.findAll((Specification<ObjectiveEntity>) any(), any(PageRequest.class)))
                .thenReturn(createObjectiveEntityList());

        var result = objectiveService.findAllObjectives(input);

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getContent().get(0).getUuid());
        assertEquals(DESCRIPTION, result.getContent().get(0).getDescription());
        assertEquals(NAME, result.getContent().get(0).getName());
        assertEquals(STATUS, result.getContent().get(0).getStatus());
        assertEquals(COMMENT, result.getContent().get(0).getComments());
        assertEquals(INSTANT_LOCAL_DATE, result.getContent().get(0).getDueToDate());
        assertEquals(TEST_UUID, result.getContent().get(0).getEmployee().getUuid());
        assertEquals(TEST_UUID, result.getContent().get(0).getEmployee().getPosition().getUuid());
        assertEquals(ID, result.getContent().get(0).getId());

    }

    private Page<ObjectiveEntity> createObjectiveEntityList() {
        return new PageImpl<>(List.of(createObjectiveEntity()));
    }

    private ObjectiveEntity createObjectiveEntity() {
        return ObjectiveEntity.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .name(NAME)
                .comments(COMMENT)
                .description(DESCRIPTION)
                .dueToDate(INSTANT_LOCAL_DATE)
                .status(ObjectiveStatus.NEW)
                .employee(createEmployeeEntity())
                .build();
    }

    private EmployeeEntity createEmployeeEntity() {
        EmployeeEntity e = new EmployeeEntity();
        e.setId(ID);
        e.setUuid(TEST_UUID);
        e.setPosition(createEmployeePositionEntity());
        e.setFirstName(NAME);
        e.setLastName(NAME);
        e.setBirthday(INSTANT_LOCAL_DATE);
        return e;
    }

    private EmployeePositionEntity createEmployeePositionEntity() {
        return EmployeePositionEntity.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }
}
