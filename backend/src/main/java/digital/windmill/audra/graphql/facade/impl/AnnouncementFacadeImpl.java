package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.mapper.AnnouncementMapper;
import digital.windmill.audra.graphql.type.Announcement;
import digital.windmill.audra.graphql.type.input.AnnouncementsInput;
import digital.windmill.audra.graphql.type.input.CreateAnnouncementInput;
import digital.windmill.audra.graphql.type.input.DeleteAnnouncementInput;
import digital.windmill.audra.graphql.type.input.UpdateAnnouncementInput;
import digital.windmill.audra.service.AnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AnnouncementFacadeImpl implements digital.windmill.audra.graphql.facade.AnnouncementFacade {
    private AnnouncementService announcementService;
    private AnnouncementMapper announcementMapper;

    @Transactional(readOnly = true)
    public Announcement findAnnouncementByUuid(UUID uuid) {
        return announcementMapper.mapAnnouncementEntityToAnnouncement(
                announcementService.findAnnouncementByUuid(uuid)
        );
    }

    @Transactional(readOnly = true)
    public Page<Announcement> getAnnouncements(AnnouncementsInput input) {
        return announcementService.findAllAnnouncements(input)
                .map(announcementMapper::mapAnnouncementEntityToAnnouncement);
    }

    @Override
    @Transactional
    public Announcement createAnnouncement(CreateAnnouncementInput input) {
        return announcementMapper.mapAnnouncementEntityToAnnouncement(announcementService.save(
                announcementMapper.mapCreateAnnounceInputToAnnouncementEntity(input)
        ));
    }

    @Override
    @Transactional
    public Announcement updateAnnouncement(UpdateAnnouncementInput input) {
        var announcementToBeUpdated = announcementService.findAnnouncementByUuid(input.getId());
        return announcementMapper.mapAnnouncementEntityToAnnouncement(announcementService.save(
                announcementMapper.mapUpdateAnnouncementInputToAnnouncementEntity(input, announcementToBeUpdated)
        ));
    }

    @Override
    @Transactional
    public Announcement deleteAnnouncement(DeleteAnnouncementInput input) {
        var announcementToBeDeleted = announcementService.findAnnouncementByUuid(input.getId());
        return announcementMapper.mapAnnouncementEntityToAnnouncement(
                announcementService.deleteAnnouncement(announcementToBeDeleted)
        );
    }


}
