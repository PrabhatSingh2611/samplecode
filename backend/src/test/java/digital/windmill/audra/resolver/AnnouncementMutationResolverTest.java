package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.AnnouncementFacade;
import digital.windmill.audra.graphql.resolver.announcement.AnnouncementMutationResolver;
import digital.windmill.audra.graphql.type.Announcement;
import digital.windmill.audra.graphql.type.input.CreateAnnouncementInput;
import digital.windmill.audra.graphql.type.input.DeleteAnnouncementInput;
import digital.windmill.audra.graphql.type.input.UpdateAnnouncementInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnnouncementMutationResolverTest {

    @Mock
    private AnnouncementFacade announcementFacade;
    @InjectMocks
    AnnouncementMutationResolver resolver;

    @Test
    void shouldCreateAnnouncement(
            @Mock CreateAnnouncementInput input,
            @Mock Announcement announcement) {
        when(announcementFacade.createAnnouncement(any(CreateAnnouncementInput.class)))
                .thenReturn(announcement);

        var result = resolver.createAnnouncement(input);
        assertNotNull(result);
        assertSame(announcement, result.getItem());
    }

    @Test
    void shouldUpdateAnnouncement(
            @Mock UpdateAnnouncementInput input,
            @Mock Announcement announcement) {
        when(announcementFacade.updateAnnouncement(any(UpdateAnnouncementInput.class)))
                .thenReturn(announcement);

        var result = resolver.updateAnnouncement(input);
        assertNotNull(result);
        assertSame(announcement, result.getItem());
    }

    @Test
    void shouldDeleteAnnouncement(
            @Mock DeleteAnnouncementInput input,
            @Mock Announcement announcement) {
        when(announcementFacade.deleteAnnouncement(any(DeleteAnnouncementInput.class)))
                .thenReturn(announcement);

        var result = resolver.deleteAnnouncement(input);
        assertNotNull(result);
        assertSame(announcement, result.getAnnouncement());
    }
}
