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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeePositionMutationResolverTest {

    @Mock
    private EmployeePositionFacade employeePositionFacade;

    @InjectMocks
    private EmployeePositionMutationResolver employeePositionMutationResolver;

    @Test
    void shouldCreateEmployeePosition(@Mock CreateEmployeePositionInput input,
                                      @Mock EmployeePosition employeePosition) {

        when(employeePositionFacade.createEmployeePosition(any(CreateEmployeePositionInput.class)))
                .thenReturn(employeePosition);

        var result = employeePositionMutationResolver.createEmployeePosition(input);
        assertNotNull(result);
        assertSame(employeePosition, result.getItem());
    }

    @Test
    void shouldUpdateEmployeePosition(@Mock UpdateEmployeePositionInput input,
                                      @Mock EmployeePosition employeePosition) {

        when(employeePositionFacade.updateEmployeePosition(any(UpdateEmployeePositionInput.class)))
                .thenReturn(employeePosition);

        var result = employeePositionMutationResolver.updateEmployeePosition(input);
        assertNotNull(result);
        assertSame(employeePosition, result.getItem());
    }

    @Test
    void shouldDeleteEmployeePosition(@Mock DeleteEmployeePositionInput input,
                                      @Mock EmployeePosition employeePosition) {

        when(employeePositionFacade.deleteEmployeePosition(any(DeleteEmployeePositionInput.class)))
                .thenReturn(employeePosition);

        var result = employeePositionMutationResolver.deleteEmployeePosition(input);
        assertNotNull(result);
        assertSame(employeePosition, result.getEmployeePosition());
    }

}
