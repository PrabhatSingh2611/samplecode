package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.EmployeeFacade;
import digital.windmill.audra.graphql.resolver.employee.EmployeeResolver;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeResolverTest {

    @Mock
    private EmployeeFacade employeeFacade;

    @InjectMocks
    private EmployeeResolver employeeResolver;

    @Test
    void shouldGetAllEmployees(@Mock EmployeesInput input,
                               @Mock Employee employee) {

        Page<Employee> pagedResponse = createOneItemPage(employee);
        when(employeeFacade.getEmployees(input)).thenReturn(pagedResponse);

        var result = employeeResolver.employees(input);
        assertNotNull(result);
        assertEquals(pagedResponse.getContent(), result.getItems());
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

}

