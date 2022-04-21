package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.graphql.mapper.EmployeePositionMapperImpl;
import digital.windmill.audra.graphql.type.input.CreateEmployeePositionInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



@ExtendWith(MockitoExtension.class)
public class EmployeePositionMapperTest {

    @InjectMocks
    private EmployeePositionMapperImpl mapper;

    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String NAME = "name";
    private static final Long ID = 1L;


    @Test
    void shouldMapEmployeePositionEntityToEmployeePosition() {
        var actual = mapper.mapEmployeePositionEntityToEmployeePosition(createEmployeePositionEntity());

        assertNotNull(actual);
        assertAll(
                () -> assertEquals(TEST_UUID, actual.getUuid()),
                () -> assertEquals(NAME, actual.getName())
        );
    }

    @Test
    void shouldMapEmployeePositionToEmployeePositionEntity() {
        var actual = mapper.mapCreateEmployeePositionInputToEmployeePositionEntity(testCreateEmployeePosition());
        assertAll(
                () -> assertEquals(NAME, actual.getName())
        );
    }

    private CreateEmployeePositionInput testCreateEmployeePosition() {
        return CreateEmployeePositionInput.builder().name(NAME).build();
    }

    private EmployeePositionEntity createEmployeePositionEntity() {
        EmployeePositionEntity e = new EmployeePositionEntity();
        e.setId(ID);
        e.setUuid(TEST_UUID);
        e.setName(NAME);

        return e;
    }
}
