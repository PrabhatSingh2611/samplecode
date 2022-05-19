package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.entity.enums.LeaveRequestStatus;
import digital.windmill.audra.graphql.mapper.LocationMapperImpl;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class LocationMapperTest {

    private static final LeaveRequestStatus STATUS = LeaveRequestStatus.NEW;
    private static final String TEXT = "EQSW";
    private static final UUID TEST_UUID = UUID.randomUUID();
    private static final Long ID = 620L;

    @InjectMocks
    private LocationMapperImpl mapper;

    @Test
    void mapLocationEntityToLocation() {
        var result = mapper.mapLocationEntityToLocation(createLocationEntity());

        assertNotNull(result);
        assertEquals(TEXT, result.getName());
        assertEquals(TEST_UUID, result.getId());
    }

    @Test
    void mapCreateLocationInputToLocationEntity() {
        var result = mapper.mapCreateLocationInputToLocationEntity(testCreateLocationInput());

        assertNotNull(result);
        assertEquals(TEXT, result.getName());
    }

    private CreateLocationInput testCreateLocationInput() {
        CreateLocationInput l = new CreateLocationInput();
        l.setName(TEXT);
        return l;
    }

    private LocationEntity createLocationEntity() {
        return LocationEntity.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .name(TEXT)
                .build();
    }
}