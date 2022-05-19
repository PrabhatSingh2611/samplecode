package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.AnnouncementEntity;
import digital.windmill.audra.graphql.type.input.AnnouncementsInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface AnnouncementService {

    AnnouncementEntity findAnnouncementByUuid(UUID uuid);

    AnnouncementEntity save(AnnouncementEntity announcementEntity);

    AnnouncementEntity deleteAnnouncement(AnnouncementEntity announcementEntity);

    Page<AnnouncementEntity> findAllAnnouncements(AnnouncementsInput input);
}
