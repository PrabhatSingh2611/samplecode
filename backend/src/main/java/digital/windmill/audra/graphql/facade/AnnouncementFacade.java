package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.Announcement;
import digital.windmill.audra.graphql.type.input.AnnouncementsInput;
import digital.windmill.audra.graphql.type.input.CreateAnnouncementInput;
import digital.windmill.audra.graphql.type.input.DeleteAnnouncementInput;
import digital.windmill.audra.graphql.type.input.UpdateAnnouncementInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface AnnouncementFacade {

    /**
     * This method will create an Announcement by a specific value.
     *
     * @param input input of which Announcement should be created
     * @return created Announcement
     */
    Announcement createAnnouncement(CreateAnnouncementInput input);

    /**
     * This method will update an Announcement by a specific value.
     *
     * @param input input of which Announcement we should update
     * @return updated Announcement
     */
    Announcement updateAnnouncement(UpdateAnnouncementInput input);

    /**
     * This method will delete an Announcement by a specific value
     *
     * @param input input of which Announcement we should delete
     * @return a specific deleted Announcement
     */
    Announcement deleteAnnouncement(DeleteAnnouncementInput input);

    /**
     * This method will return an Announcement by a specific uuid.
     *
     * @param uuid of which Announcement we should look
     * @return a specific Announcement
     */
    Announcement findAnnouncementByUuid(UUID uuid);

    /**
     * This method will return a list of Announcements.
     *
     * @param input input for query result
     * @return a list of Announcement
     */
    Page<Announcement> getAnnouncements(AnnouncementsInput input);
}
