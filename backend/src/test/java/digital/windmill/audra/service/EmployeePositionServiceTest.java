package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.repository.EmployeePositionRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.EmployeePositionMapper;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.input.CreateEmployeePositionInput;
import digital.windmill.audra.graphql.type.input.UpdateEmployeePositionInput;
import digital.windmill.audra.service.impl.EmployeePositionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeePositionServiceTest {


    @Mock
    private EmployeePositionRepository employeePositionRepository;
    @Mock
    private EmployeePositionMapper employeePositionMapper;

    @InjectMocks
    private EmployeePositionServiceImpl service;

    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String NAME = "fJC3qxuU";
    private static final Long ID = 990L;


    @Test
    void shouldCreateEmployeePosition() {
        when(employeePositionMapper.mapCreateEmployeePositionInputToEmployeePositionEntity(any(CreateEmployeePositionInput.class))).thenReturn(createEmployeePositionEntity());
        when(employeePositionRepository.save(any(EmployeePositionEntity.class))).thenReturn(createEmployeePositionEntity());
        when(employeePositionMapper.mapEmployeePositionEntityToEmployeePosition(any(EmployeePositionEntity.class))).thenReturn(createEmployeePosition());

        var result = service.createEmployeePosition(createEmployeePositionInput());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(NAME, result.getName());
        Assertions.assertEquals(TEST_UUID, result.getUuid());
    }

    @Test
    void shouldUpdateEmployeePosition() {
        EmployeePositionEntity entity = createEmployeePositionEntity();
        entity.setName("XaN");
        when(employeePositionRepository.save(any(EmployeePositionEntity.class))).thenReturn(entity);
        when(employeePositionMapper.mapEmployeePositionEntityToEmployeePosition(any(EmployeePositionEntity.class)))
                .thenReturn(createEmployeePosition());
        when(employeePositionMapper.mapUpdateToEmployeePositionEntity(any(UpdateEmployeePositionInput.class)
                ,any(EmployeePosition.class)))
                .thenReturn(createEmployeePositionEntity());

        var result = service.updateEmployeePosition(updateEmployeePositionInput(), createEmployeePosition());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getUuid());
        Assertions.assertEquals(NAME, result.getName());

    }

    @Test
    void shouldDeleteEmployeePosition() {

        doNothing().when(employeePositionRepository).delete(any(EmployeePositionEntity.class));
        when(employeePositionMapper.mapEmployeePositionEntityToEmployeePosition(any(EmployeePositionEntity.class)))
                .thenReturn(createEmployeePosition());
        when(employeePositionMapper.mapEmployeePositionToEmployeePositionEntity(any(EmployeePosition.class)))
                .thenReturn(createEmployeePositionEntity());

        var result = service.deleteEmployeePosition(createEmployeePosition());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getUuid());
        Assertions.assertEquals(NAME, result.getName());
    }

    @Test
    void shouldFindByUuid() {
        when(employeePositionRepository.findByUuid(any(UUID.class))).thenReturn(Optional.ofNullable(createEmployeePositionEntity()));
        when(employeePositionMapper.mapEmployeePositionEntityToEmployeePosition(any(EmployeePositionEntity.class)))
                .thenReturn(createEmployeePosition());
        var result = service.findEmployeePositionByUuid(TEST_UUID);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getUuid());

    }

    @Test
    void shouldThrowNPEWhenUuidIsNull() {
        when(employeePositionRepository.findByUuid(any(UUID.class))).thenThrow(new DataNotFoundException("location not found for :" + TEST_UUID));
        Assertions.assertThrows(DataNotFoundException.class, () -> service.findEmployeePositionByUuid(TEST_UUID));
    }


    private CreateEmployeePositionInput createEmployeePositionInput() {
        return CreateEmployeePositionInput.builder().name(NAME).build();
    }

    private UpdateEmployeePositionInput updateEmployeePositionInput() {
        return UpdateEmployeePositionInput.builder().uuid(TEST_UUID).name(NAME).build();
    }


    private EmployeePositionEntity createEmployeePositionEntity() {
        return EmployeePositionEntity.builder().id(ID).uuid(TEST_UUID).name(NAME).build();
    }

    private EmployeePosition createEmployeePosition() {
        return EmployeePosition.builder().id(ID).uuid(TEST_UUID).name(NAME).build();
    }

}


