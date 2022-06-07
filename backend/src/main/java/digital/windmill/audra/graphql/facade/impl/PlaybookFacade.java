package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.graphql.mapper.PlaybookMapper;
import digital.windmill.audra.graphql.type.Playbook;
import digital.windmill.audra.graphql.type.input.CreatePlaybookInput;
import digital.windmill.audra.graphql.type.input.DeletePlaybookInput;
import digital.windmill.audra.graphql.type.input.PatchPlaybookInput;
import digital.windmill.audra.graphql.type.input.PlaybooksInput;
import digital.windmill.audra.service.ResourceService;
import digital.windmill.audra.service.impl.PlaybookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PlaybookFacade {

    private PlaybookService playbookService;
    private ResourceService resourceService;
    private PlaybookMapper playbookMapper;

    @Transactional(readOnly = true)
    public Playbook findPlaybookByUuid(UUID uuid) {
        return playbookMapper.map(
                playbookService.findPlaybookByUuid(uuid)
        );
    }

    @Transactional(readOnly = true)
    public Page<Playbook> getPlaybooks(PlaybooksInput input) {
        return playbookService.findAll(input)
                .map(playbookMapper::map);
    }

    @Transactional
    public Playbook createPlaybook(CreatePlaybookInput input) {
        var image = resourceService.findResourceByUuid(input.getImage().getId()).orElse(null);
        return playbookMapper.map(playbookService.save(
                playbookMapper.mapToCreate(input, image)
        ));
    }

    @Transactional
    public Playbook patchPlaybook(PatchPlaybookInput input) {
        var playbookToBeUpdated = playbookService.findPlaybookByUuid(input.getId());
        ResourceEntity resourceEntity = null;
        if (Objects.nonNull(input.getImage()) && Objects.nonNull(input.getImage().getId())) {
            resourceEntity = resourceService.findResourceByUuid(input.getImage().getId()).orElse(null);
        }
        return playbookMapper.map(playbookService.save(
                playbookMapper.mapToPatch(input, playbookToBeUpdated, resourceEntity)
        ));
    }

    @Transactional
    public Playbook deletePlaybook(DeletePlaybookInput input) {
        var playbookToBeDeleted = playbookService.findPlaybookByUuid(input.getId());
        return playbookMapper.map(
                playbookService.deletePlaybook(playbookToBeDeleted)
        );
    }
}
