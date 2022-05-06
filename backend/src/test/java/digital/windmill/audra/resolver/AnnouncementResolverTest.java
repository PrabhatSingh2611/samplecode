package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.AnnouncementFacade;
import digital.windmill.audra.graphql.resolver.announcement.AnnouncementResolver;
import digital.windmill.audra.graphql.type.Announcement;
import digital.windmill.audra.graphql.type.input.AnnouncementInput;
import digital.windmill.audra.graphql.type.input.AnnouncementsInput;
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
class AnnouncementResolverTest {

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private AnnouncementFacade announcementFacade;

    @InjectMocks
    AnnouncementResolver resolver;

    @Test
    void shouldGetAnnouncement(
            @Mock AnnouncementInput input,
            @Mock Announcement announcement) {
        when(input.getUuid()).thenReturn(TEST_UUID);
        when(announcementFacade.findAnnouncementByUuid(any(UUID.class)))
                .thenReturn(announcement);

        var result = resolver.announcement(input);
        assertNotNull(result);
        assertSame(announcement, result.getItem());
    }

    @Test
    void shouldGetAnnouncements(
            @Mock AnnouncementsInput input,
            @Mock Announcement announcement) {
        var pagedAnnouncements = createOneItemPage(announcement);
        when(announcementFacade.getAnnouncements(any(AnnouncementsInput.class)))
                .thenReturn(pagedAnnouncements);

        var result = resolver.announcements(input);
        assertNotNull(result);
        assertSame(announcement, result.getItems().get(0));
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

}
