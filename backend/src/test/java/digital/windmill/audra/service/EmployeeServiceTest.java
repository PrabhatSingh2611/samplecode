package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.dao.repository.EmployeeRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.mapper.EmployeePositionMapper;
import digital.windmill.audra.graphql.mapper.LocationMapper;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import digital.windmill.audra.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeMapper employeeMapper;
    @Mock
    private LocationMapper locationMapper;
    @Mock
    private EmployeePositionMapper employeePositionMapper;

    @InjectMocks
    private EmployeeServiceImpl service;

    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String NAME = "BuA1VXU";
    private static final Long ID = 1L;
    private static final String ROLE = "z9Qtg5d";
    private final static ZonedDateTime BIRTHDAY_ZONED_DATE_TIME = ZonedDateTime.now();
    private final static Instant LOCAL_DATE = Instant.now();


    @Test
    void shouldCreateEmployee(@Mock CreateEmployeeInput input,
                              @Mock EmployeeEntity employeeEntity,
                              @Mock EmployeePosition employeePosition,
                              @Mock Location location) {

        when(employeePositionMapper.mapEmployeePositionToEmployeePositionEntity(any(EmployeePosition.class)))
                .thenReturn(createEmployeePositionEntity());
        when(locationMapper.mapLocationToLocationEntity(any(Location.class))).thenReturn(createLocationEntity());
        when(employeeMapper.mapEmployeeInputToEmployeeEntity(any(CreateEmployeeInput.class),
                any(EmployeeEntity.class),
                any(EmployeePositionEntity.class),
                any(LocationEntity.class)))
                .thenReturn(createEmployeeEntity());

        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(createEmployeeEntity());

        when(employeeMapper.mapEmployeeEntityToEmployee(any(EmployeeEntity.class)))
                .thenReturn((createEmployeePojo()));

        var result = service.createEmployee(input, employeeEntity, employeePosition, location);

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(NAME, result.getLastName());
        assertEquals(NAME, result.getFirstName());
        assertEquals(NAME, result.getLocation().getName());
        assertEquals(NAME, result.getPosition().getName());
        assertEquals(ROLE, result.getRole());
        assertEquals(BIRTHDAY_ZONED_DATE_TIME, result.getBirthday());
    }

    @Test
    void shouldFindEmployeeByUuid() {
        when(employeeRepository.findByUuid(any(UUID.class))).thenReturn(Optional.ofNullable(createEmployeeEntity()));

        var result = service.findEmployeeByUuid(TEST_UUID);

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(TEST_UUID, result.getLocation().getUuid());
        assertEquals(NAME, result.getPosition().getName());
        assertEquals(LOCAL_DATE, result.getBirthday());
    }

    @Test
    void shouldThrowDataNotFoundWhenUuidIsNull() {
        when(employeeRepository.findByUuid(any(UUID.class)))
                .thenThrow(new DataNotFoundException("location not found for :" + TEST_UUID));
        Assertions.assertThrows(DataNotFoundException.class, () -> service.findEmployeeByUuid(TEST_UUID));
    }

    @Test
    void shouldReturnNullWhenEmployeeIsNull() {
        var result = service.findEmployeeByUuid(null);
        assertNull(result);
    }

    private EmployeePosition createEmployeePosition() {
        return EmployeePosition.builder().id(ID).uuid(TEST_UUID).name(NAME).build();
    }

    private Location createLocation() {
        return Location.builder()
                .id(ID)
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
        e.setFirstName(NAME);
        e.setLastName(NAME);
        e.setUuid(TEST_UUID);
        e.setRole(EmployeeRole.EMPLOYEE);
        e.setLocation(createLocationEntity());
        e.setPosition(createEmployeePositionEntity());
        e.setBirthday(LOCAL_DATE);
        return e;
    }

    private Employee createEmployeePojo() {
        return Employee.builder()
                .uuid(TEST_UUID)
                .role(ROLE)
                .firstName(NAME)
                .lastName(NAME)
                .position(createEmployeePosition())
                .location(createLocation())
                .birthday(BIRTHDAY_ZONED_DATE_TIME)
                .build();
    }
}

