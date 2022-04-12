package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.repository.EmployeePositionRepository;
import digital.windmill.audra.dao.repository.EmployeeRepository;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
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
    private EmployeePositionRepository employeePositionRepository;

    @InjectMocks
    private EmployeeService service;

    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String FIRST_NAME = "9e3";
    private static final String LAST_NAME = "q4YP7xH";
    private static final String NAME = "BuA1VXU";
    private static final Long ID = 1L;
    private final static Instant BIRTHDAY = Instant.now();
    private final static ZonedDateTime BIRTHDAY_ZONED_DATE_TIME = ZonedDateTime.now();


    @Test
    void shouldCreateEmployee() {

        when(employeeRepository.save(any(EmployeeEntity.class)))
                .thenReturn(createEmployeeEntity());
        when(employeePositionRepository.findByUuid(any(UUID.class)))
                .thenReturn(createEmployeePositionEntity());

        var result = service.createEmployee(createCreateEmployeeInput());

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(ID, result.getId());
        assertEquals(FIRST_NAME, result.getFirstName());
        assertEquals(LAST_NAME, result.getLastName());
    }

    private CreateEmployeeInput createCreateEmployeeInput() {
        return CreateEmployeeInput.builder()
                .position(TEST_UUID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .birthday(BIRTHDAY_ZONED_DATE_TIME)
                .build();
    }

    private Optional<EmployeePositionEntity> createEmployeePositionEntity() {
        return Optional.of(EmployeePositionEntity.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .name(NAME)
                .build());
    }

    private EmployeeEntity createEmployeeEntity() {
        EmployeeEntity e = new EmployeeEntity();
        e.setId(1L);
        e.setUuid(TEST_UUID);
        e.setFirstName(FIRST_NAME);
        e.setLastName(LAST_NAME);
        e.setBirthday(BIRTHDAY);
        return e;
    }


}

