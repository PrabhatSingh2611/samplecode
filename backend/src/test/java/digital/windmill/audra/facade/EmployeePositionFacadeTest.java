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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeePositionFacadeTest {

    @Mock
    private EmployeePositionService employeePositionService;
    @Mock
    private EmployeePositionMapper employeePositionMapper;
    @InjectMocks
    private EmployeePositionFacadeImpl facade;

    @Test
    void shouldCreateEmployeePosition(@Mock CreateEmployeePositionInput input,
                                      @Mock EmployeePositionEntity employeePositionEntity,
                                      @Mock EmployeePosition employeePosition) {
        when(employeePositionMapper.mapCreateEmployeePositionInputToEmployeePositionEntity(input))
                .thenReturn(employeePositionEntity);
        when(employeePositionService.save(employeePositionEntity)).thenReturn(employeePositionEntity);
        when(employeePositionMapper.mapEmployeePositionEntityToEmployeePosition(employeePositionEntity))
                .thenReturn(employeePosition);

        var actualResult = facade.createEmployeePosition(input);

        assertEquals(employeePosition, actualResult);
    }

    @Test
    void shouldUpdateEmployeePosition(@Mock UpdateEmployeePositionInput input,
                                      @Mock EmployeePositionEntity employeePositionEntity,
                                      @Mock EmployeePosition employeePosition) {
        when(employeePositionService.findEmployeePositionByUuid(input.getId())).thenReturn(employeePositionEntity);
        when(employeePositionService.save(employeePositionEntity)).thenReturn(employeePositionEntity);
        when(employeePositionMapper.mapEmployeePositionEntityToEmployeePosition(employeePositionEntity))
                .thenReturn(employeePosition);

        var actualResult = facade.updateEmployeePosition(input);

        assertEquals(employeePosition, actualResult);

    }

    @Test
    void deleteEmployeePosition(@Mock DeleteEmployeePositionInput input,
                                @Mock EmployeePositionEntity employeePositionEntity,
                                @Mock EmployeePosition employeePosition) {
        when(employeePositionService.findEmployeePositionByUuid(input.getId())).thenReturn(employeePositionEntity);
        when(employeePositionService.deleteEmployeePosition(employeePositionEntity)).thenReturn(employeePositionEntity);
        when(employeePositionMapper.mapEmployeePositionEntityToEmployeePosition(employeePositionEntity))
                .thenReturn(employeePosition);

        var actualResult = facade.deleteEmployeePosition(input);

        assertEquals(employeePosition, actualResult);
    }

}