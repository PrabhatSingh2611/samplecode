package digital.windmill.audra.facade;

import digital.windmill.audra.graphql.facade.EmployeePositionFacade;
import digital.windmill.audra.graphql.mapper.EmployeePositionMapper;
import digital.windmill.audra.service.EmployeePositionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeePositionFacadeTest {

    @Mock
    private EmployeePositionService employeePositionService;

    @Mock
    private EmployeePositionMapper employeePositionMapper;

    @InjectMocks
    EmployeePositionFacade facade;



}
