package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.graphql.facade.impl.EmployeePositionFacadeImpl;
import digital.windmill.audra.graphql.mapper.EmployeePositionMapper;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.input.CreateEmployeePositionInput;
import digital.windmill.audra.graphql.type.input.DeleteEmployeePositionInput;
import digital.windmill.audra.graphql.type.input.UpdateEmployeePositionInput;
import digital.windmill.audra.service.EmployeePositionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.print.attribute.standard.MediaSize;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeePositionFacadeTest {

    @Mock
    private EmployeePositionService employeePositionService;

    @Mock
    private EmployeePositionMapper employeePositionMapper;

    @InjectMocks
    private EmployeePositionFacadeImpl facade;

    private static final UUID TEST_UUID = UUID.fromString("beefbb78-b6af-4270-9a94-86626b036fb0");
    private static final String NAME = "audra";
    private static final Long ID = 214L;

    @Test
    void shouldCreateEmployeePosition() {
        when(employeePositionService.createEmployeePosition(any(CreateEmployeePositionInput.class)))
                .thenReturn(testEmployeePosition());

        var result = facade.createEmployeePosition(testCreateEmployeePositionInput());

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(NAME, result.getName());
    }

    @Test
    void shouldUpdateEmployeePosition() {
        when(employeePositionService.findByUuid(any(UUID.class))).thenReturn(createEmployeePositionEntity());
        when(employeePositionService.updateEmployeePosition(
                any(UpdateEmployeePositionInput.class),
                any(EmployeePositionEntity.class)))
                .thenReturn(testEmployeePosition());

        var result = facade.updateEmployeePosition(testUpdateEmployeePositionInput());

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(NAME, result.getName());

    }

    @Test
    void deleteEmployeePosition() {
        when(employeePositionService.findByUuid(any(UUID.class))).thenReturn(createEmployeePositionEntity());
        when(employeePositionService.deleteEmployeePosition(any(EmployeePositionEntity.class)))
                .thenReturn(testEmployeePosition());

        var result = facade.deleteEmployeePosition(testDeleteEmployeePositionInput());

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getUuid());
    }

    private EmployeePositionEntity createEmployeePositionEntity() {
        return EmployeePositionEntity.builder()
                .id(ID)
                .name(NAME)
                .uuid(TEST_UUID)
                .build();
    }

    private DeleteEmployeePositionInput testDeleteEmployeePositionInput() {
        return DeleteEmployeePositionInput.builder()
                .uuid(TEST_UUID)
                .build();
    }

    private UpdateEmployeePositionInput testUpdateEmployeePositionInput() {
        return UpdateEmployeePositionInput.builder()
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }

    private CreateEmployeePositionInput testCreateEmployeePositionInput() {
        CreateEmployeePositionInput c = new CreateEmployeePositionInput();
        c.setName(NAME);
        return c;
    }

    private EmployeePosition testEmployeePosition() {
        return EmployeePosition.builder()
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }
}