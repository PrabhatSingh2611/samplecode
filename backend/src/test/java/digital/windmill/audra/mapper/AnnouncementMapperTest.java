package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.AnnouncementEntity;
import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.graphql.mapper.AnnouncementMapperImpl;
import digital.windmill.audra.graphql.mapper.DateTimeMapper;
import digital.windmill.audra.graphql.type.input.CreateAnnouncementInput;
import digital.windmill.audra.graphql.type.input.UpdateAnnouncementInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnnouncementMapperTest {
    private static final UUID TEST_UUID = UUID.fromString("755b97b4-bb9a-4318-bf3a-a23646e592af");
    private static final Long ID = 22L;
    private static final String TEXT = "9AMj3X";
    private static final EmployeeRole ROLE = EmployeeRole.ADMIN;
    private static final ZoneId zone = ZoneId.systemDefault();
    private final static ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.of(2020, 2, 12, 7, 55, 20, 100, zone);
    private final static Instant INSTANT = ZONED_DATE_TIME.toInstant();

    @Mock
    private DateTimeMapper dateTimeMapper;
    @InjectMocks
    AnnouncementMapperImpl mapper;

    @Test
    void shouldMapAnnouncementEntityToAnnouncement() {
        when(dateTimeMapper.map(any(Instant.class))).thenReturn(ZONED_DATE_TIME);
        var result = mapper.mapAnnouncementEntityToAnnouncement(createAnnouncementEntity());
        assertNotNull(result);
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(TEXT, result.getBody());
        assertEquals(ZONED_DATE_TIME, result.getCreatedAt());
    }

    @Test
    void shouldMapInputToAnnouncementEntity() {

        var result = mapper.mapCreateAnnounceInputToAnnouncementEntity(createAnnouncementInputPojo());
        assertNotNull(result);
        assertEquals(TEXT, result.getBody());
    }

    @Test
    void shouldMapInputToAnnouncementEntityWhenUpdate() {

        var result = mapper.mapUpdateAnnouncementInputToAnnouncementEntity(
                updateAnnouncementInputPojo(), createAnnouncementEntity());
        assertNotNull(result);
        assertEquals(TEXT, result.getBody());
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(INSTANT, result.getCreatedAt());
    }

    private UpdateAnnouncementInput updateAnnouncementInputPojo() {
        return UpdateAnnouncementInput.builder()
                .body(TEXT)
                .uuid(TEST_UUID)
                .build();
    }

    private CreateAnnouncementInput createAnnouncementInputPojo() {
        return CreateAnnouncementInput.builder()
                .body(TEXT)
                .build();
    }

    private AnnouncementEntity createAnnouncementEntity() {
        AnnouncementEntity e = new AnnouncementEntity();
        e.setId(ID);
        e.setUuid(TEST_UUID);
        e.setBody(TEXT);
        e.setCreatedAt(INSTANT);
        return e;
    }
}
