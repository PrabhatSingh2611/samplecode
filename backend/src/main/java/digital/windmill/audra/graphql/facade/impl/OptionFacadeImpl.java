package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.OptionFacade;
import digital.windmill.audra.graphql.mapper.OptionMapper;
import digital.windmill.audra.graphql.type.QuestionOption;
import digital.windmill.audra.service.OptionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class OptionFacadeImpl implements OptionFacade {

    private OptionService service;
    private OptionMapper optionMapper;

    @Transactional(readOnly = true)
    public Page<QuestionOption> getOptions(UUID uuid) {
        return service.findAllOptions(uuid)
                .map(optionMapper::mapOptionEntityToOption);
    }
}
