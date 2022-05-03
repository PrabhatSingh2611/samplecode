package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.ObjectiveEntity;
import digital.windmill.audra.dao.entity.enums.ObjectiveStatus;
import digital.windmill.audra.graphql.facade.impl.ObjectiveFacadeImpl;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.mapper.ObjectiveMapper;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.DeleteObjectiveInput;
import digital.windmill.audra.graphql.type.input.ObjectivesInput;
import digital.windmill.audra.graphql.type.input.UpdateObjectiveInput;
import digital.windmill.audra.service.EmployeeService;
import digital.windmill.audra.service.ObjectiveService;
import liquibase.pro.packaged.T;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObjectiveFacadeTest {
    private static final UUID OBJECTIVE_UUID = UUID.randomUUID();
    private static final UUID EMPLOYEE_UUID = UUID.randomUUID();
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
                               @Mock Objective objective,
                               @Mock EmployeeEntity employeeEntity) {
        when(input.getEmployee()).thenReturn(EMPLOYEE_UUID);
        when(employeeService.findEmployeeByUuid(any(UUID.class))).thenReturn(employeeEntity);
        when(objectiveMapper.mapObjectiveInputToEntity(any(CreateObjectiveInput.class), any(EmployeeEntity.class)))
                .thenReturn(objectiveEntity);
        when(objectiveMapper.mapObjectiveEntityToObjective(any(ObjectiveEntity.class))).thenReturn(objective);
        when(objectiveService.createObjective(any(ObjectiveEntity.class))).thenReturn(objectiveEntity);

        var result = facade.createObjective(input);

        assertNotNull(result);
        assertSame(objective, result);
    }

    @Test
    void shouldUpdateObjective(@Mock UpdateObjectiveInput input,
                               @Mock ObjectiveEntity objectiveEntity,
                               @Mock Objective objective,
                               @Mock EmployeeEntity employeeEntity) {
        when(input.getEmployee()).thenReturn(EMPLOYEE_UUID);
        when(input.getUuid()).thenReturn(OBJECTIVE_UUID);
        when(employeeService.findEmployeeByUuid(any(UUID.class)))
                .thenReturn(employeeEntity);
        when(objectiveService.findObjectiveByUuid(any(UUID.class)))
                .thenReturn(objectiveEntity);
        when(objectiveMapper.mapInputToEntityWhenUpdate(any(UpdateObjectiveInput.class),
                any(ObjectiveEntity.class),
                any(EmployeeEntity.class))).thenReturn(objectiveEntity);
        when(objectiveMapper.mapObjectiveEntityToObjective(any(ObjectiveEntity.class)))
                .thenReturn(objective);
        when(objectiveService.updateObjective(any(ObjectiveEntity.class)))
                .thenReturn(objectiveEntity);

        var result = facade.updateObjective(input);

        assertNotNull(result);
        assertSame(objective, result);
    }

    @Test
    void shouldDeleteObjective(@Mock DeleteObjectiveInput input,
                               @Mock ObjectiveEntity objectiveEntity,
                               @Mock Objective objective) {
        when(input.getUuid()).thenReturn(OBJECTIVE_UUID);
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
        when(objectiveService.findAllObjectives(any(ObjectivesInput.class)))
                .thenReturn(objectivePage);
        when(objectiveMapper.mapObjectiveEntityToObjective(any(ObjectiveEntity.class)))
                .thenReturn(objective);

        var result = facade.getObjectives(input);
        assertNotNull(result);
        assertEquals(objectivePage.getContent().get(0).getUuid(), result.getContent().get(0).getUuid());
        assertEquals(objectivePage.getContent().get(0).getId(), result.getContent().get(0).getId());
        assertEquals(objectivePage.getContent().get(0).getComments(), result.getContent().get(0).getComments());
        assertEquals(objectivePage.getContent().get(0).getDescription(), result.getContent().get(0).getDescription());
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }
}