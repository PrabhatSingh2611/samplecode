package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.mapper.PlaybookMapper;
import digital.windmill.audra.graphql.mapper.PlaybookSectionMapper;
import digital.windmill.audra.graphql.type.DeletePlaybookSectionInput;
import digital.windmill.audra.graphql.type.PatchPlaybookSectionInput;
import digital.windmill.audra.graphql.type.Playbook;
import digital.windmill.audra.graphql.type.PlaybookSection;
import digital.windmill.audra.graphql.type.ReorderPlaybookSectionInput;
import digital.windmill.audra.graphql.type.input.CreatePlaybookSectionInput;
import digital.windmill.audra.service.PlaybookSectionService;
import digital.windmill.audra.service.impl.PlaybookService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaybookSectionFacade {

    private final PlaybookSectionMapper playbookSectionMapper;
    private final PlaybookMapper playbookMapper;
    private final PlaybookSectionService playbookSectionService;
    private final PlaybookService playbookService;

    @Transactional
    public PlaybookSection createPlaybookSection(@NonNull CreatePlaybookSectionInput input) {
        var playbookEntity = playbookService.findPlaybookByUuid(input.getPlaybook().getId());
        var nextSortPosition = playbookSectionService.getNextSortPosition(playbookEntity);
        var playbookSectionEntity = playbookSectionMapper.map(input, playbookEntity, nextSortPosition);
        var savedPlaybookSection = playbookSectionService.save(playbookSectionEntity);
        return playbookSectionMapper.map(savedPlaybookSection);
    }

    @Transactional
    public PlaybookSection patchPlaybookSection(@NonNull PatchPlaybookSectionInput input) {
        var playbookSectionEntity = playbookSectionService.findByUuid(input.getId());
        var updatedPlaybookSectionEntity = playbookSectionMapper.map(playbookSectionEntity, input);
        var savedPlaybookSection = playbookSectionService.save(updatedPlaybookSectionEntity);
        return playbookSectionMapper.map(savedPlaybookSection);
    }

    @Transactional
    public PlaybookSection deletePlaybookSection(@NonNull DeletePlaybookSectionInput input) {
        var playbookSectionEntity = playbookSectionService.findByUuid(input.getId());
        playbookSectionService.delete(playbookSectionEntity);
        return playbookSectionMapper.map(playbookSectionEntity);
    }

    @Transactional
    public Playbook reorderPlaybookSection(ReorderPlaybookSectionInput input) {
        var playbookSectionEntity = playbookSectionService.findByUuid(input.getId());
        var afterPlaybookSectionEntity = Optional.ofNullable(input.getAfterId())
                .map(playbookSectionService::findByUuid)
                .orElse(null);
        playbookSectionService.reorder(playbookSectionEntity, afterPlaybookSectionEntity);
        return playbookMapper.map(playbookService.findPlaybookByUuid(playbookSectionEntity.getPlaybook().getUuid()));
    }
}
