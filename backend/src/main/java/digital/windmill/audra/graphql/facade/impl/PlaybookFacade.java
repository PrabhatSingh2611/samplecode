package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.mapper.PlaybookMapper;
import digital.windmill.audra.graphql.type.Playbook;
import digital.windmill.audra.graphql.type.input.PlaybooksInput;
import digital.windmill.audra.service.impl.PlaybookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PlaybookFacade {

    private PlaybookService playbookService;
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
    
}
