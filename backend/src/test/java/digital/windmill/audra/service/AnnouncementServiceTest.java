package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.AnnouncementEntity;
import digital.windmill.audra.dao.repository.AnnouncementRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.input.AnnouncementsInput;
import digital.windmill.audra.service.impl.AnnouncementServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnnouncementServiceTest {

    private static final UUID TEST_UUID = UUID.randomUUID();
    private static final ZoneId zone = ZoneId.systemDefault();
    private final static ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.of(2020, 2, 12, 7, 55, 20, 100, zone);

    @Mock
    private AnnouncementRepository announcementRepository;

    @InjectMocks
    AnnouncementServiceImpl service;

    @Test
    void shouldReturnAnnouncementByUuid(@Mock AnnouncementEntity announcementEntity) {
        when(announcementRepository.findByUuid(any(UUID.class)))
                .thenReturn(Optional.ofNullable(announcementEntity));

        var result = service.findAnnouncementByUuid(TEST_UUID);
        assertNotNull(result);
        assertSame(announcementEntity, result);
    }

    @Test
    void shouldThrowDataNotFound() {
        Assertions.assertThrows(DataNotFoundException.class, () -> service.findAnnouncementByUuid(TEST_UUID));
    }

    @Test
    void shouldReturnAllAnnouncement(@Mock AnnouncementsInput input, @Mock AnnouncementEntity announcementEntity) {
        var announcementEntityPage = createOneItemPage(announcementEntity);
        when(announcementRepository.findAll(any(Specification.class), any(PageRequest.class)))
                .thenReturn(announcementEntityPage);
        var result = service.findAllAnnouncements(input);
        assertNotNull(result);
        assertSame(announcementEntityPage, result);
    }


    @Test
    void shouldSaveOrUpdateAnnouncement(@Mock AnnouncementEntity announcementEntity) {
        when(announcementRepository.save(any(AnnouncementEntity.class))).thenReturn(announcementEntity);

        var result = service.save(announcementEntity);
        assertNotNull(result);
        assertSame(announcementEntity, result);
    }

    @Test
    void shouldDeleteAnnouncement(@Mock AnnouncementEntity announcementEntity) {
        var result = service.deleteAnnouncement(announcementEntity);
        assertNotNull(result);
        assertSame(announcementEntity, result);
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }
}
