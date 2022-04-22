package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.EmployeePositionFacade;
import digital.windmill.audra.graphql.resolver.employee.EmployeePositionMutationResolver;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.input.CreateEmployeePositionInput;
import digital.windmill.audra.graphql.type.input.UpdateEmployeePositionInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeePositionMutationResolverTest {

    @Mock
    private EmployeePositionFacade facade;

    @InjectMocks
    private EmployeePositionMutationResolver employeePositionMutationResolver;

    private static final UUID TEST_UUID = UUID.fromString("c16cf92a-6a78-46fc-bd4c-c0a92135bd26");
    private static final String NAME = "x4W8hai";

    @Test
    void shouldCreateEmployeePosition() {
        when(facade.createEmployeePosition(any(CreateEmployeePositionInput.class)))
                .thenReturn(createEmployeePosition());

        var result = employeePositionMutationResolver.createEmployeePosition(createEmployeePositionInput());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getItem().getUuid());
        Assertions.assertEquals(NAME, result.getItem().getName());
    }

    private EmployeePosition createEmployeePosition() {
        return EmployeePosition
                .builder()
                .uuid(TEST_UUID)
                .id(1L)
                .name(NAME)
                .build();
    }

    @Test
    void shouldUpdateEmployeePosition() {

        when(facade.updateEmployeePosition(any(UpdateEmployeePositionInput.class)))
                .thenReturn(createEmployeePosition());
        var result = employeePositionMutationResolver.updateEmployeePosition(updateEmployeePositionInput());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getItem().getUuid());
        Assertions.assertEquals(NAME, result.getItem().getName());
    }

    private UpdateEmployeePositionInput updateEmployeePositionInput() {
        return UpdateEmployeePositionInput
                .builder()
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }

    private CreateEmployeePositionInput createEmployeePositionInput() {
        return CreateEmployeePositionInput
                .builder()
                .name(NAME)
                .build();
    }

}
