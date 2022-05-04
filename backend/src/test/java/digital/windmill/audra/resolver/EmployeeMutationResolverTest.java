package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.EmployeeFacade;
import digital.windmill.audra.graphql.resolver.employee.EmployeeMutationResolver;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
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
class EmployeeMutationResolverTest {

    @Mock
    private EmployeeFacade employeeFacade;

    @InjectMocks
    private EmployeeMutationResolver employeeMutationResolver;

    @Test
    void shouldCreateEmployee(@Mock CreateEmployeeInput input,
                              @Mock Employee employee) {

        when(employeeFacade.createEmployee(any(CreateEmployeeInput.class)))
                .thenReturn(employee);

        var result = employeeMutationResolver.createEmployee(input);
        assertNotNull(result);
        assertSame(employee, result.getItem());
    }
}

