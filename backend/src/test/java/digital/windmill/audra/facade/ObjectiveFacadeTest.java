package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.ObjectiveEntity;
import digital.windmill.audra.graphql.facade.impl.ObjectiveFacadeImpl;
import digital.windmill.audra.graphql.mapper.ObjectiveMapper;
import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.DeleteObjectiveInput;
import digital.windmill.audra.graphql.type.input.ObjectivesInput;
import digital.windmill.audra.graphql.type.input.UpdateObjectiveInput;
import digital.windmill.audra.service.EmployeeService;
import digital.windmill.audra.service.ObjectiveService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObjectiveFacadeTest {
    private static final UUID OBJECTIVE_UUID = UUID.randomUUID();
    @Mock
    private ObjectiveService objectiveService;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private ObjectiveMapper objectiveMapper;
    @InjectMocks
    ObjectiveFacadeImpl facade;

    @Test
    void shouldCreateObjective(@Mock CreateObjectiveInput input,
                               @Mock ObjectiveEntity objectiveEntity,
                               @Mock EmployeeEntity employeeEntity,
                               @Mock Objective objective) {
        when(employeeService.findEmployeeByUuid(input.getEmployee())).thenReturn(employeeEntity);
        when(objectiveMapper.mapObjectiveInputToEntity(input, employeeEntity)).thenReturn(objectiveEntity);
        when(objectiveService.save(objectiveEntity)).thenReturn(objectiveEntity);
        when(objectiveMapper.mapObjectiveEntityToObjective(objectiveEntity)).thenReturn(objective);

        var actualResult = facade.createObjective(input);

        assertEquals(objective, actualResult);
    }

    @Test
    void shouldUpdateObjective(@Mock UpdateObjectiveInput input,
                               @Mock ObjectiveEntity objectiveEntity,
                               @Mock EmployeeEntity employeeEntity,
                               @Mock Objective objective) {
        when(employeeService.findEmployeeByUuid(input.getEmployee())).thenReturn(employeeEntity);
        when(objectiveService.findObjectiveByUuid(input.getId())).thenReturn(objectiveEntity);
        when(objectiveMapper.mapInputToEntityWhenUpdate(input, objectiveEntity, employeeEntity)).thenReturn(objectiveEntity);
        when(objectiveService.save(objectiveEntity)).thenReturn(objectiveEntity);
        when(objectiveMapper.mapObjectiveEntityToObjective(objectiveEntity)).thenReturn(objective);

        var actualResult = facade.updateObjective(input);

        assertEquals(objective, actualResult);
    }

    @Test
    void shouldDeleteObjective(@Mock DeleteObjectiveInput input,
                               @Mock ObjectiveEntity objectiveEntity,
                               @Mock Objective objective) {
        when(input.getId()).thenReturn(OBJECTIVE_UUID);
        when(objectiveService.findObjectiveByUuid(any(UUID.class)))
                .thenReturn(objectiveEntity);
        when(objectiveMapper.mapObjectiveEntityToObjective(any(ObjectiveEntity.class)))
                .thenReturn(objective);
        when(objectiveService.deleteObjective(any(ObjectiveEntity.class)))
                .thenReturn(objectiveEntity);


        var result = facade.deleteObjective(input);
        assertNotNull(result);
        assertSame(objective, result);
    }

    @Test
    void shouldFindObjectiveByUuid(
            @Mock ObjectiveEntity objectiveEntity,
            @Mock Objective objective) {
        when(objectiveService.findObjectiveByUuid(any(UUID.class)))
                .thenReturn(objectiveEntity);

        when(objectiveMapper.mapObjectiveEntityToObjective(any(ObjectiveEntity.class)))
                .thenReturn(objective);

        var result = facade.findObjectiveByUuid(OBJECTIVE_UUID);
        assertNotNull(result);
        assertSame(objective, result);
    }

    @Test
    void shouldGetAllObjectives(@Mock ObjectivesInput input,
                                @Mock ObjectiveEntity objectiveEntity,
                                @Mock Objective objective) {


        Page<ObjectiveEntity> objectivePage = createOneItemPage(objectiveEntity);
        when(objectiveService.findAllObjectives(any(ObjectivesInput.class))).thenReturn(objectivePage);
        when(objectiveMapper.mapObjectiveEntityToObjective(any(ObjectiveEntity.class))).thenReturn(objective);

        var actualResult = facade.getObjectives(input);

        assertEquals(createOneItemPage(objective), actualResult);
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }
}