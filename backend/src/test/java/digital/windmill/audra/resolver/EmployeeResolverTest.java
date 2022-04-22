package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.EmployeeFacade;
import digital.windmill.audra.graphql.resolver.employee.EmployeeResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeResolverTest {

    @Mock
    private EmployeeFacade facade;

    @InjectMocks
    private EmployeeResolver employeeResolver;

    @Test
    void testEmployees(){
    }
}
