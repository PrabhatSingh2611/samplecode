package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.graphql.mapper.DateTimeMapper;
import digital.windmill.audra.graphql.mapper.EmployeeMapperImpl;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class EmployeeMapperTest {

    @InjectMocks
    private EmployeeMapperImpl mapper;

    @Mock
    private DateTimeMapper zonedDateTime;

    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final Long ID = 1L;
    private static final String NAME = "Name";
    private static final Integer ITEM_PER_PAGE = 3;
    private static final Integer PAGE_NUMBER = 2;
    private final static Instant LOCAL_DATE = Instant.now();
    private static final EmployeeRole ROLE = EmployeeRole.ADMIN;
    private static final EmployeeRole ENUM_ROLE = EmployeeRole.ADMIN;
    private static final String POSITION = "Position";
    private final static ZonedDateTime DATE_TIME = ZonedDateTime.now();

    @Test
    void shouldMapEmployeeEntityToEmployee() {
        Employee actual = mapper.map(createEmployeeEntity());
        assertAll(
                () -> assertEquals(TEST_UUID, actual.getUuid()),
                () -> assertEquals(NAME, actual.getFirstName())
        );
    }

    @Test
    void shouldMapCreateEmployeeInputToEmployee() {
        EmployeeEntity result = mapper.map(createCreateEmployeeInput(),
                createEmployeeEntity(),
                createEmployeePositionEntity(),
                createLocationEntity());

        assertNotNull(result);
        assertAll(
                ()->assertEquals(NAME, result.getFirstName()),
                ()->assertEquals(createLocationEntity().getName(), result.getLocation().getName()),
                ()->assertEquals(createEmployeePositionEntity().getName(), result.getPosition().getName())

        );

    }

    private EmployeePositionEntity createEmployeePositionEntity() {
        return EmployeePositionEntity.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }

    private CreateEmployeeInput createCreateEmployeeInput() {
        return CreateEmployeeInput.builder()
                .location(TEST_UUID)
                .position(TEST_UUID)
                .reportingManager(TEST_UUID)
                .firstName(NAME)
                .lastName(NAME)
                .role(ROLE)
                .birthday(DATE_TIME)
                .build();
    }

    private EmployeeEntity createEmployeeEntity() {
        EmployeeEntity e = new EmployeeEntity();
        e.setUuid(TEST_UUID);
        e.setFirstName(NAME);
        e.setLastName(NAME);
        e.setBirthday(LOCAL_DATE);
        e.setId(1L);
        e.setPosition(createPosition());
        e.setLocation(createLocationEntity());

        return e;

    }

    private LocationEntity createLocationEntity() {
        LocationEntity e = new LocationEntity();
        e.setId(1L);
        e.setName(NAME);
        return e;
    }

    private EmployeePositionEntity createPosition() {
        return EmployeePositionEntity.builder()
                .id(1L)
                .uuid(TEST_UUID)
                .name(NAME)
                .build();
    }
}
