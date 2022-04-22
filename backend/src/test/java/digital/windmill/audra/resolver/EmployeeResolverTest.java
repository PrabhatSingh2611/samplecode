package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.EmployeeFacade;
import digital.windmill.audra.graphql.resolver.employee.EmployeeResolver;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;


import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeResolverTest {

    @Mock
    private EmployeeFacade facade;

    @InjectMocks
    private EmployeeResolver employeeResolver;

    private static final Long ID = 58L;
    private static final UUID TEST_UUID = UUID.fromString("2c1a5bff-657c-40ff-93cd-6c44f1d9b6ee");
    private static final String TEST = "Fr60l";
    private static final ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.now();

    @Test
    void shouldGetAllEmployees(@Mock EmployeesInput input) {
        List<Employee> employees = List.of(createEmployee());
        var pagedResponse = new PageImpl(employees);
        when(facade.getEmployees(any(EmployeesInput.class))).thenReturn(pagedResponse);
        var result = employeeResolver.employees(input);
        assertNotNull(result);
        assertEquals(TEST_UUID, result.getItems().get(0).getUuid());
        assertEquals(TEST, result.getItems().get(0).getFirstName());
        assertEquals(TEST, result.getItems().get(0).getLastName());
        assertEquals(ZONED_DATE_TIME, result.getItems().get(0).getBirthday());
        assertEquals(TEST_UUID, result.getItems().get(0).getLocation().getUuid());
        assertEquals(TEST, result.getItems().get(0).getPosition().getName());
        assertEquals(TEST, result.getItems().get(0).getRole());
    }

    private Employee createEmployee() {
        return Employee.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .firstName(TEST)
                .lastName(TEST)
                .role(TEST)
                .birthday(ZONED_DATE_TIME)
                .location(createLocation())
                .position(createPosition())
                .build();
    }

    private EmployeePosition createPosition() {
        return EmployeePosition.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .name(TEST)
                .build();
    }

    private Location createLocation() {
        return Location.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .name(TEST)
                .build();
    }
}

