package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.graphql.mapper.EmployeePositionMapperImpl;
import digital.windmill.audra.graphql.type.EmployeePosition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class EmployeePositionMapperTest {

    @InjectMocks
    private EmployeePositionMapperImpl mapper;

    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String NAME = "name";


    @Test
    void shouldMap() {
        EmployeePosition actual = mapper.mapEmployeePositionEntityToEmployeePosition(createEmployeePositionEntity());
        assertAll(
                () -> assertEquals(TEST_UUID, actual.getUuid()),
                () -> assertEquals(NAME, actual.getName())
        );
    }

    private EmployeePositionEntity createEmployeePositionEntity() {
        EmployeePositionEntity e = new EmployeePositionEntity();
        e.setId(1L);
        e.setUuid(TEST_UUID);
        e.setName(NAME);

        return e;
    }
}
