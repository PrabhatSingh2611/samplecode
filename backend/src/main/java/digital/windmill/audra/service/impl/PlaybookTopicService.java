package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.entity.PlaybookSectionEntity;
import digital.windmill.audra.dao.entity.PlaybookSectionTopicEntity;
import digital.windmill.audra.dao.repository.PlaybookTopicRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class PlaybookTopicService {

    private PlaybookTopicRepository repository;

    public PlaybookSectionTopicEntity findTopicByUuid(UUID uuid) {
        return repository.findTopicByUuid(uuid).orElseThrow(
                () -> new DataNotFoundException("Playbook topic not available for given UUID : " + uuid.toString())
        );
    }

    public PlaybookSectionTopicEntity save(PlaybookSectionTopicEntity entity) {
        return repository.save(entity);
    }

    public PlaybookSectionTopicEntity delete(PlaybookSectionTopicEntity entity) {
        repository.delete(entity);
        return entity;
    }

    public int getNextSortPosition(PlaybookSectionEntity playbookSectionEntity) {
        return repository.getLastPostPosition(playbookSectionEntity)
                .map(i -> i + 1)
                .orElse(1);
    }

    public void reorder(PlaybookSectionTopicEntity playbookTopicEntity,
                        PlaybookSectionTopicEntity afterPlaybookTopicEntity,
                        PlaybookSectionEntity sectionEntity) {

        var afterSortPosition = Optional.ofNullable(afterPlaybookTopicEntity)
                .map(PlaybookSectionTopicEntity::getSort)
                .orElse(0);

        repository.shiftSort(sectionEntity, afterSortPosition);
        playbookTopicEntity.setSort(afterSortPosition + 1);
        playbookTopicEntity.setSection(sectionEntity);
        repository.save(playbookTopicEntity);
    }
}
