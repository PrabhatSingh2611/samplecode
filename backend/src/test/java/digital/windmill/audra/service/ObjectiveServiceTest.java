package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.ObjectiveEntity;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObjectiveServiceTest {
    private static final Long ID = 1L;
    private static final UUID OBJECTIVE_UUID = UUID.randomUUID();
    @Mock
    private ObjectiveRepository objectiveRepository;
    @InjectMocks
    ObjectiveServiceImpl objectiveService;

    @Test
    void shouldCreateObjective(@Mock ObjectiveEntity objectiveEntity) {
        when(objectiveRepository.save(any(ObjectiveEntity.class))).thenReturn(objectiveEntity);

        var result = objectiveService.save(objectiveEntity);

        assertNotNull(result);
        assertSame(objectiveEntity, result);
    }

    @Test
    void shouldUpdateObjective(@Mock ObjectiveEntity objectiveEntity) {
        when(objectiveRepository.save(any(ObjectiveEntity.class))).thenReturn(objectiveEntity);

        var result = objectiveService.save(objectiveEntity);
        assertNotNull(result);
        assertSame(objectiveEntity, result);
    }

    @Test
    void shouldDeleteObjective(@Mock ObjectiveEntity objectiveEntity) {
        var result = objectiveService.deleteObjective(objectiveEntity);
        assertNotNull(result);
        assertSame(objectiveEntity, result);
    }

    @Test
    void shouldReturnObjectiveByUuid(@Mock ObjectiveEntity objectiveEntity) {
        when(objectiveRepository.findObjectiveByUuid(any(UUID.class)))
                .thenReturn(Optional.ofNullable(objectiveEntity));

        var result = objectiveService.findObjectiveByUuid(OBJECTIVE_UUID);
        assertNotNull(result);
        assertSame(objectiveEntity, result);
    }

    @Test
    void shouldThrowDataNotFoundWhenUuidIsNull() {
        when(objectiveRepository.findObjectiveByUuid(any(UUID.class)))
                .thenThrow(new DataNotFoundException("Objective not found for :" + OBJECTIVE_UUID));
        Assertions.assertThrows(DataNotFoundException.class, () -> objectiveService.findObjectiveByUuid(OBJECTIVE_UUID));
    }

    @Test
    void shouldReturnAllObjective(@Mock ObjectivesInput input, @Mock ObjectiveEntity objectiveEntity) {

        Page<ObjectiveEntity> objectiveEntityPage = createOneItemPage(objectiveEntity);
        when(objectiveRepository.findAll((Specification<ObjectiveEntity>) any(), any(PageRequest.class)))
                .thenReturn(objectiveEntityPage);

        var result = objectiveService.findAllObjectives(input);
        assertNotNull(result);
        assertSame(objectiveEntityPage, result);
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }
}