package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.mapper.DateTimeMapper;
import digital.windmill.audra.graphql.mapper.EmployeeMapperImpl;
import digital.windmill.audra.graphql.type.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EmployeeMapperTest {

    @InjectMocks
    private EmployeeMapperImpl mapper;

    @Mock
    private DateTimeMapper zonedDateTime;

    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String NAME = "name";
    private final static Instant LOCALE_DATE_TIME = Instant.now();

    @Test
    void shouldMap() {
        Employee actual = mapper.map(createEmployeeEntity());
        assertAll(
                () -> assertEquals(TEST_UUID, actual.getUuid()),
                () -> assertEquals(NAME, actual.getFirstName())
        );
    }

    private EmployeeEntity createEmployeeEntity() {
        EmployeeEntity e = new EmployeeEntity();
        e.setUuid(TEST_UUID);
        e.setFirstName(NAME);
        e.setLastName(NAME);
        e.setBirthday(LOCALE_DATE_TIME);
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
