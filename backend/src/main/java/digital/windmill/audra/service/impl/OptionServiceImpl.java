package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.OptionSpecification;
import digital.windmill.audra.dao.entity.OptionEntity;
import digital.windmill.audra.dao.repository.OptionRepository;
import digital.windmill.audra.dao.repository.QuestionRepository;
import digital.windmill.audra.service.OptionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class OptionServiceImpl implements OptionService {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private QuestionRepository questionRepository;
    private OptionRepository optionRepository;


    @Override
    public Page<OptionEntity> findAllOptions(UUID uuid) {
        var questionEntity = questionRepository.findQuestionByUuid(uuid);
        var specification = OptionSpecification.byOptions(questionEntity.get().getOptions());
        var pageable = PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
        return optionRepository.findAll(specification, pageable);
    }

}
