package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.entity.enums.LeaveRequestStatus;
import digital.windmill.audra.graphql.mapper.LocationMapperImpl;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LocationMapperTest {

    @InjectMocks
    private LocationMapperImpl mapper;

    private static final LeaveRequestStatus STATUS = LeaveRequestStatus.NEW;
    private static final String TEXT = "EQSW";
    private static final UUID TEST_UUID = UUID.fromString("b3a998bf-f067-40fd-a292-827b57226517");
    private static final Long ID = 620L;
    private static final Instant INSTANT_DATE = Instant.now();

    @Test
    void mapLocationEntityToLocation() {
        var result = mapper.mapLocationEntityToLocation(createLocationEntity());

        assertNotNull(result);
        assertEquals(TEXT, result.getName());
        assertEquals(TEST_UUID, result.getUuid());
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