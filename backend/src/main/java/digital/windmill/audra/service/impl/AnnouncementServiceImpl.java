package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.AnnouncementSpecification;
import digital.windmill.audra.dao.entity.AnnouncementEntity;
import digital.windmill.audra.dao.repository.AnnouncementRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.input.AnnouncementsInput;
import digital.windmill.audra.graphql.type.input.PageInput;
import digital.windmill.audra.service.AnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private AnnouncementRepository announcementRepository;

    @Override
    public AnnouncementEntity findAnnouncementByUuid(UUID uuid) {
        return announcementRepository.findByUuid(uuid).orElseThrow(
                () -> new DataNotFoundException("Announcement not available for given UUID : " + uuid)
        );
    }

    @Override
    public Page<AnnouncementEntity> findAllAnnouncements(AnnouncementsInput input) {
        var specification = AnnouncementSpecification.byAnnouncementsInput(input);
        var itemsPerPage = Optional.of(input).map(AnnouncementsInput::getPagination).
                map(PageInput::getItemsPerPage).orElse(DEFAULT_PAGE_SIZE);
        var pageNumber = Optional.of(input).map(AnnouncementsInput::getPagination).
                map(PageInput::getPageNumber).orElse(DEFAULT_PAGE_NUMBER);
        var pageable = PageRequest.of(pageNumber, itemsPerPage);
        return announcementRepository.findAll(specification, pageable);
    }

    @Override
    public AnnouncementEntity save(AnnouncementEntity announcementEntity) {
        return announcementRepository.save(announcementEntity);
    }


    @Override
    public AnnouncementEntity deleteAnnouncement(AnnouncementEntity announcementEntity) {
        announcementRepository.delete(announcementEntity);
        return announcementEntity;
    }
}
