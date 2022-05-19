package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.repository.EmployeePositionRepository;
import digital.windmill.audra.service.impl.EmployeePositionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeePositionServiceTest {

    @Mock
    private EmployeePositionRepository employeePositionRepository;

    @InjectMocks
    private EmployeePositionServiceImpl service;

    private static final UUID TEST_UUID = UUID.randomUUID();


    @Test
    void shouldSave(@Mock EmployeePositionEntity employeePositionEntity) {
        when(employeePositionRepository.save(employeePositionEntity)).thenReturn(employeePositionEntity);
        var actualResult = service.save(employeePositionEntity);
        assertEquals(employeePositionEntity, actualResult);
    }

    @Test
    void shouldDeleteEmployeePosition(@Mock EmployeePositionEntity employeePositionEntity) {
        var actualResult = service.deleteEmployeePosition(employeePositionEntity);
        assertEquals(employeePositionEntity, actualResult);
        verify(employeePositionRepository).delete(employeePositionEntity);
    }

    @Test
    void shouldFindByUuid(@Mock EmployeePositionEntity employeePositionEntity) {
        when(employeePositionRepository.findByUuid(any(UUID.class))).thenReturn(Optional.ofNullable(employeePositionEntity));
        var result = service.findEmployeePositionByUuid(TEST_UUID);
        Assertions.assertEquals(employeePositionEntity, result);
    }

}


