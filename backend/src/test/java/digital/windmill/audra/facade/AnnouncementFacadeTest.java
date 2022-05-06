package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.AnnouncementEntity;
import digital.windmill.audra.graphql.facade.impl.AnnouncementFacadeImpl;
import digital.windmill.audra.graphql.mapper.AnnouncementMapper;
import digital.windmill.audra.graphql.type.Announcement;
import digital.windmill.audra.graphql.type.input.AnnouncementsInput;
import digital.windmill.audra.graphql.type.input.CreateAnnouncementInput;
import digital.windmill.audra.graphql.type.input.DeleteAnnouncementInput;
import digital.windmill.audra.graphql.type.input.UpdateAnnouncementInput;
import digital.windmill.audra.service.AnnouncementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnnouncementFacadeTest {

    private static final UUID ANNOUNCEMENT_UUID = UUID.randomUUID();

    @Mock
    private AnnouncementService announcementService;
    @Mock
    private AnnouncementMapper announcementMapper;
    @InjectMocks
    AnnouncementFacadeImpl facade;

    @Test
    void shouldFindAnnouncementByUuid(
            @Mock AnnouncementEntity announcementEntity,
            @Mock Announcement announcement) {

        when(announcementService.findAnnouncementByUuid(any(UUID.class))).thenReturn(announcementEntity);
        when(announcementMapper.mapAnnouncementEntityToAnnouncement(any(AnnouncementEntity.class)))
                .thenReturn(announcement);

        var result = facade.findAnnouncementByUuid(ANNOUNCEMENT_UUID);
        assertNotNull(result);
        assertSame(announcement, result);
    }

    @Test
    void shouldGetAnnouncements(
            @Mock AnnouncementsInput input,
            @Mock Announcement announcement,
            @Mock AnnouncementEntity announcementEntity) {

        var pagedResponse = createOneItemPage(announcementEntity);
        when(announcementService.findAllAnnouncements(any(AnnouncementsInput.class)))
                .thenReturn(pagedResponse);
        when(announcementMapper.mapAnnouncementEntityToAnnouncement(any(AnnouncementEntity.class)))
                .thenReturn(announcement);

        var result = facade.getAnnouncements(input);
        assertNotNull(result);
        assertSame(announcement, result.getContent().get(0));
    }

    @Test
    void shouldCreateAnnouncement(
            @Mock CreateAnnouncementInput input,
            @Mock Announcement announcement,
            @Mock AnnouncementEntity announcementEntity) {

        when(announcementMapper.mapAnnouncementEntityToAnnouncement(any(AnnouncementEntity.class)))
                .thenReturn(announcement);
        when(announcementMapper.mapCreateAnnounceInputToAnnouncementEntity(any(CreateAnnouncementInput.class)))
                .thenReturn(announcementEntity);
        when(announcementService.saveOrUpdateAnnouncement(any(AnnouncementEntity.class)))
                .thenReturn(announcementEntity);

        var result = facade.createAnnouncement(input);
        assertNotNull(result);
        assertSame(announcement, result);
    }

    @Test
    void shouldUpdateAnnouncement(
            @Mock UpdateAnnouncementInput input,
            @Mock Announcement announcement,
            @Mock AnnouncementEntity announcementEntity) {

        when(input.getUuid()).thenReturn(ANNOUNCEMENT_UUID);
        when(announcementService.findAnnouncementByUuid(any(UUID.class))).thenReturn(announcementEntity);
        when(announcementMapper.mapAnnouncementEntityToAnnouncement(any(AnnouncementEntity.class)))
                .thenReturn(announcement);
        when(announcementMapper.mapUpdateAnnouncementInputToAnnouncementEntity(any(UpdateAnnouncementInput.class),
                any(AnnouncementEntity.class)))
                .thenReturn(announcementEntity);
        when(announcementService.saveOrUpdateAnnouncement(any(AnnouncementEntity.class)))
                .thenReturn(announcementEntity);

        var result = facade.updateAnnouncement(input);
        assertNotNull(result);
        assertSame(announcement, result);
    }

    @Test
    void shouldDeleteAnnouncement(
            @Mock DeleteAnnouncementInput input,
            @Mock Announcement announcement,
            @Mock AnnouncementEntity announcementEntity) {

        when(input.getUuid()).thenReturn(ANNOUNCEMENT_UUID);
        when(announcementService.findAnnouncementByUuid(any(UUID.class))).thenReturn(announcementEntity);
        when(announcementMapper.mapAnnouncementEntityToAnnouncement(any(AnnouncementEntity.class)))
                .thenReturn(announcement);
        when(announcementService.deleteAnnouncement(any(AnnouncementEntity.class)))
                .thenReturn(announcementEntity);

        var result = facade.deleteAnnouncement(input);
        assertNotNull(result);
        assertSame(announcement, result);
    }

    private <T> Page<T> createOneItemPage(T item) {

        return new PageImpl<>(List.of(item));
    }

}
