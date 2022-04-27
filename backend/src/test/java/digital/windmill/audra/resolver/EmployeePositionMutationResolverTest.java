package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.EmployeePositionFacade;
import digital.windmill.audra.graphql.resolver.employee.EmployeePositionMutationResolver;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.input.CreateEmployeePositionInput;
import digital.windmill.audra.graphql.type.input.DeleteEmployeePositionInput;
import digital.windmill.audra.graphql.type.input.UpdateEmployeePositionInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeePositionMutationResolverTest {

    private static final UUID TEST_UUID = UUID.randomUUID();
    private static final String NAME = "x4W8hai";

    @Mock
    private EmployeePositionFacade facade;

    @InjectMocks
    private EmployeePositionMutationResolver employeePositionMutationResolver;

    @Test
    void shouldCreateEmployeePosition(@Mock CreateEmployeePositionInput input) {
        when(facade.createEmployeePosition(any(CreateEmployeePositionInput.class)))
                .thenReturn(createEmployeePosition());

        var result = employeePositionMutationResolver.createEmployeePosition(input);

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getItem().getUuid());
        assertEquals(NAME, result.getItem().getName());
    }

    @Test
    void shouldUpdateEmployeePosition(@Mock UpdateEmployeePositionInput input) {

        when(facade.updateEmployeePosition(any(UpdateEmployeePositionInput.class)))
                .thenReturn(createEmployeePosition());

        var result = employeePositionMutationResolver.updateEmployeePosition(input);

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getItem().getUuid());
        assertEquals(NAME, result.getItem().getName());
    }

    @Test
    void shouldDeleteEmployeePosition(@Mock DeleteEmployeePositionInput input) {
        when(facade.deleteEmployeePosition(any(DeleteEmployeePositionInput.class)))
                .thenReturn(createEmployeePosition());

        var result = employeePositionMutationResolver.deleteEmployeePosition(input);

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getEmployeePosition().getUuid());
    }

    private EmployeePosition createEmployeePosition() {
        return EmployeePosition
                .builder()
                .uuid(TEST_UUID)
                .id(1L)
                .name(NAME)
                .build();
    }
}
