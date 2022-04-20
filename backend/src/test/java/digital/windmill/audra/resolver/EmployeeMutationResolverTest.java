package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.impl.EmployeeFacadeImpl;
import digital.windmill.audra.graphql.resolver.employee.EmployeeMutationResolver;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeMutationResolverTest {

    @Mock
    private EmployeeFacadeImpl employeeFacadeImpl;

    @InjectMocks
    private EmployeeMutationResolver employeeMutationResolver;

    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String NAME = "7JGB";
    private static final String ROLE = "Admin";
    private final static ZonedDateTime DATE_TIME = ZonedDateTime.now();

    @Test
    void createEmployee() {
        when(employeeFacadeImpl.createEmployee(any(CreateEmployeeInput.class)))
                .thenReturn(createEmployeeTest());

        var result = employeeMutationResolver.createEmployee(createCreateEmployeeInput());

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getItem().getUuid());
        assertEquals(ROLE, result.getItem().getRole());
        assertEquals(NAME, result.getItem().getPosition().getName());
        assertEquals(DATE_TIME, result.getItem().getBirthday());

    }

    private CreateEmployeeInput createCreateEmployeeInput() {
        return CreateEmployeeInput.builder()
                .birthday(DATE_TIME)
                .position(TEST_UUID)
                .location(TEST_UUID)
                .firstName(NAME)
                .lastName(NAME)
                .build();
    }

    private Employee createEmployeeTest() {
        return Employee.builder()
                .uuid(TEST_UUID)
                .firstName(NAME)
                .lastName(NAME)
                .role(ROLE)
                .birthday(DATE_TIME)
                .position(createEmployeePosition())
                .location(createLocation())
                .build();
    }

    private EmployeePosition createEmployeePosition() {
        return EmployeePosition.builder().name(NAME).uuid(TEST_UUID).build();
    }

    private Location createLocation() {
        return Location.builder().uuid(TEST_UUID).name(NAME).build();
    }
}