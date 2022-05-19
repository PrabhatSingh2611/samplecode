package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.repository.EmployeeRepository;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import digital.windmill.audra.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl service;

    private static final UUID EMPLOYEE_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");


    @Test
    void save(@Mock EmployeeEntity employeeEntity) {
        when(employeeRepository.save(employeeEntity)).thenReturn(employeeEntity);
        var actualResult = service.save(employeeEntity);
        assertEquals(employeeEntity, actualResult);
    }

    @Test
    void shouldFindEmployeeByUuid(@Mock EmployeeEntity employeeEntity) {
        when(employeeRepository.findByUuid(EMPLOYEE_UUID)).thenReturn(Optional.of(employeeEntity));
        var actualResult = service.findEmployeeByUuid(EMPLOYEE_UUID);
        assertEquals(employeeEntity, actualResult);
    }

    @Test
    void shouldGetEmployees(@Mock EmployeesInput input,
                            @Mock EmployeeEntity employeeEntity) {
        var pageEmployees = new PageImpl<>(List.of(employeeEntity));
        when(employeeRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(pageEmployees);
        var actualResult = service.getEmployees(input);
        assertEquals(pageEmployees, actualResult);
    }

}

