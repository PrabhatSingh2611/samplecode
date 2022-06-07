package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.PlaybookEntity;
import digital.windmill.audra.dao.entity.PlaybookSectionEntity;
import digital.windmill.audra.dao.repository.PlaybookSectionRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PlaybookSectionService {
    private final PlaybookSectionRepository playbookSectionRepository;

    public PlaybookSectionEntity save(@NonNull PlaybookSectionEntity playbookSectionEntity) {
        return playbookSectionRepository.save(playbookSectionEntity);
    }

    public PlaybookSectionEntity findByUuid(@NonNull UUID uuid) {
        return playbookSectionRepository.findOneByUuid(uuid)
                .orElseThrow(() -> new DataNotFoundException("Playbook Section not available for given UUID : " + uuid.toString()));
    }

    public void delete(@NonNull PlaybookSectionEntity playbookSectionEntity) {
        playbookSectionRepository.delete(playbookSectionEntity);
    }

    public int getNextSortPosition(@NonNull PlaybookEntity playbookEntity) {
        return playbookSectionRepository.getLastSortPosition(playbookEntity)
                .map(i -> i + 1)
                .orElse(1);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void reorder(@NonNull PlaybookSectionEntity playbookSectionEntity,
                        PlaybookSectionEntity afterPlaybookSectionEntity) {
        var afterSortPosition = Optional.ofNullable(afterPlaybookSectionEntity)
                .map(PlaybookSectionEntity::getSort)
                .orElse(0);
        playbookSectionRepository.shiftSort(playbookSectionEntity.getPlaybook(), afterSortPosition);
        playbookSectionEntity.setSort(afterSortPosition + 1);
        playbookSectionRepository.save(playbookSectionEntity);
    }
}
