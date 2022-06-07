package digital.windmill.audra.graphql.resolver.employee;

import digital.windmill.audra.graphql.facade.EmployeePositionFacade;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.input.EmployeePositionsInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeePositionQueryResolverTest {

    @InjectMocks
    private EmployeePositionQueryResolver employeePositionQueryResolver;

    @Mock
    private EmployeePositionFacade employeePositionFacade;

    @Test
    void employeePositions(@Mock EmployeePositionsInput input,
                           @Mock EmployeePosition employeePosition) {
        when(employeePositionFacade.getEmployeePositions(input)).thenReturn(new PageImpl<>(List.of(employeePosition)));

        var actualResult = employeePositionQueryResolver.employeePositions(input);

        assertEquals(List.of(employeePosition), actualResult.getItems());
        assertEquals(1, actualResult.getTotalItems());
        assertEquals(1, actualResult.getPageInfo().currentPage());
        assertEquals(1, actualResult.getPageInfo().totalPages());
    }
}