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

    private static final UUID TEST_UUID = UUID.randomUUID();
    private static final String NAME = "Fr60l";
    private static final String ROLE = "Admin";
    private static final ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.now();

    @Mock
    private EmployeeFacade facade;

    @InjectMocks
    private EmployeeResolver employeeResolver;

    @Test
    void shouldGetAllEmployees(@Mock EmployeesInput input) {
        List<Employee> employees = List.of(createEmployee());
        var pagedResponse = new PageImpl<>(employees);
        when(facade.getEmployees(input)).thenReturn(pagedResponse);

        var result = employeeResolver.employees(input);

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getItems().get(0).getUuid());
        assertEquals(NAME, result.getItems().get(0).getFirstName());
        assertEquals(ZONED_DATE_TIME, result.getItems().get(0).getBirthday());
        assertEquals(TEST_UUID, result.getItems().get(0).getLocation().getUuid());
        assertEquals(NAME, result.getItems().get(0).getPosition().getName());
       }

    private Employee createEmployee() {
        return Employee.builder()
                .id(1L)
                .uuid(TEST_UUID)
                .firstName(NAME)
                .lastName(NAME)
                .role(ROLE)
                .birthday(ZONED_DATE_TIME)
                .location(createLocation())
                .position(createPosition())
                .build();
    }

    private EmployeePosition createPosition() {
        return EmployeePosition.builder()
                .id(1L)
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }

    private Location createLocation() {
        return Location.builder()
                .id(1L)
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }
}

