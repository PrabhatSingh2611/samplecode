package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.AnnouncementEntity;
import digital.windmill.audra.graphql.type.input.AnnouncementsInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface AnnouncementService {

    /**
     * This method will return a specific Announcement by specific UUID.
     *
     * @param uuid uuid by which we search AnnouncementEntity
     * @return a specific AnnouncementEntity
     */
    AnnouncementEntity findAnnouncementByUuid(UUID uuid);

    /**
     * This method Create Announcement
     *
     * @param entity by which AnnouncementEntity will be created
     * @return created AnnouncementEntity
     */
    AnnouncementEntity saveOrUpdateAnnouncement(AnnouncementEntity entity);


    /**
     * This method will delete an Announcement by a specific value.
     *
     * @param entity AnnouncementEntity of which we should be deleting
     * @return deleted AnnouncementEntity
     */
    AnnouncementEntity deleteAnnouncement(AnnouncementEntity entity);

    /**
     * This method will return a list of Announcement.
     *
     * @param input for query result
     * @return a list of AnnouncementEntity including pagination
     */
    Page<AnnouncementEntity> findAllAnnouncements(AnnouncementsInput input);
}
