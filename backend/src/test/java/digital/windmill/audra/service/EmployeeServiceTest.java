package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.dao.repository.EmployeePositionRepository;
import digital.windmill.audra.dao.repository.EmployeeRepository;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import digital.windmill.audra.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeMapper employeeMapper;


    @InjectMocks
    private EmployeeServiceImpl service;

    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String NAME = "BuA1VXU";
    private static final String POSITION = "mDi";
    private static final Long ID = 1L;
    private static final String ROLE = "z9Qtg5d";
    private final static Instant BIRTHDAY = Instant.now();
    private final static ZonedDateTime BIRTHDAY_ZONED_DATE_TIME = ZonedDateTime.now();


    @Test
    void shouldCreateEmployee() {

        when(employeeRepository.save(any(EmployeeEntity.class)))
                .thenReturn(createEmployeeEntity());
        when(employeeMapper.map(any(CreateEmployeeInput.class),
                any(EmployeeEntity.class),
                any(EmployeePositionEntity.class),
                any(LocationEntity.class))).thenReturn(createEmployeeEntity());
        when(employeeMapper.map(any(EmployeeEntity.class))).thenReturn(createEmployee());

        var result = service.createEmployee(createCreateEmployeeInput(),
                createEmployeeEntity(),
                createEmployeePositionEntity(),
                createLocationEntity());

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(NAME, result.getLastName());
        assertEquals(NAME, result.getFirstName());
    }

    private Employee createEmployee() {
        return Employee.builder()
                .uuid(TEST_UUID)
                .role(ROLE)
                .firstName(NAME)
                .lastName(NAME)
                .position(POSITION)
                .location(createLocation())
                .build();
    }

    private Location createLocation() {
        return Location.builder()
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }

    private LocationEntity createLocationEntity() {
        return LocationEntity.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }

    private CreateEmployeeInput createCreateEmployeeInput() {
        return CreateEmployeeInput.builder()
                .position(TEST_UUID)
                .firstName(NAME)
                .lastName(NAME)
                .birthday(BIRTHDAY_ZONED_DATE_TIME)
                .build();
    }

    private EmployeePositionEntity createEmployeePositionEntity() {
        return EmployeePositionEntity.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }

    private EmployeeEntity createEmployeeEntity() {
        EmployeeEntity e = new EmployeeEntity();
        e.setId(1L);
        e.setUuid(TEST_UUID);
        e.setFirstName(NAME);
        e.setLastName(NAME);
        e.setBirthday(BIRTHDAY);
        return e;
    }
}

