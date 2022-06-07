package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.mapper.PlaybookSectionMapper;
import digital.windmill.audra.graphql.mapper.PlaybookTopicMapper;
import digital.windmill.audra.graphql.type.PlaybookSection;
import digital.windmill.audra.graphql.type.PlaybookSectionTopic;
import digital.windmill.audra.graphql.type.input.CreatePlaybookSectionTopicInput;
import digital.windmill.audra.graphql.type.input.DeletePlaybookSectionTopicInput;
import digital.windmill.audra.graphql.type.input.PatchPlaybookSectionTopicInput;
import digital.windmill.audra.graphql.type.input.ReorderPlaybookSectionTopicInput;
import digital.windmill.audra.service.PlaybookSectionService;
import digital.windmill.audra.service.impl.PlaybookTopicService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PlaybookTopicFacade {

    private final PlaybookTopicService service;
    private final PlaybookSectionService playbookSectionService;
    private final PlaybookTopicMapper mapper;
    private final PlaybookSectionMapper playbookSectionMapper;
    private final PlaybookSectionService sectionService;

    @Transactional(readOnly = true)
    public PlaybookSectionTopic findTopicByUuid(UUID uuid) {
        return mapper.map(
                service.findTopicByUuid(uuid)
        );
    }

    @Transactional
    public PlaybookSectionTopic createPlaybookTopic(@NonNull CreatePlaybookSectionTopicInput input) {
        var playbookSectionEntity = sectionService.findByUuid(input.getSection().getId());
        var nextSortPosition = service.getNextSortPosition(playbookSectionEntity);
        var playbookTopicEntity = mapper.map(input, playbookSectionEntity, nextSortPosition);
        var savedPlaybookTopic = service.save(playbookTopicEntity);
        return mapper.map(savedPlaybookTopic);

    }

    @Transactional
    public PlaybookSectionTopic patchPlaybookTopic(@NonNull PatchPlaybookSectionTopicInput input) {
        var playbookTopicEntity = service.findTopicByUuid(input.getId());
        var updatedPlaybookTopicEntity = mapper.map(playbookTopicEntity, input);
        var savedPlaybookTopic = service.save(updatedPlaybookTopicEntity);
        return mapper.map(savedPlaybookTopic);
    }

    @Transactional
    public PlaybookSectionTopic deletePlaybookTopic(@NonNull DeletePlaybookSectionTopicInput input) {
        var playbookTopicEntity = service.findTopicByUuid(input.getId());
        service.delete(playbookTopicEntity);
        return mapper.map(playbookTopicEntity);
    }

    @Transactional
    public PlaybookSection reorderTopic(ReorderPlaybookSectionTopicInput input) {
        var playbookTopicEntity = service.findTopicByUuid(input.getId());
        var playbookSectionEntity = playbookSectionService.findByUuid(input.getSectionId());
        var afterPlaybookTopicEntity = Optional.ofNullable(input.getAfterId())
                .map(service::findTopicByUuid)
                .orElse(null);
        service.reorder(playbookTopicEntity, afterPlaybookTopicEntity, playbookSectionEntity);
        var playbookTopicEntityAfterReordering = service.findTopicByUuid(input.getId());
        return playbookSectionMapper.map(playbookTopicEntityAfterReordering.getSection());
    }
}
