package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.PlaybookSpecification;
import digital.windmill.audra.dao.entity.PlaybookEntity;
import digital.windmill.audra.dao.repository.PlaybookRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.enums.PlaybookSort;
import digital.windmill.audra.graphql.type.input.PageInput;
import digital.windmill.audra.graphql.type.input.PlaybooksInput;
import digital.windmill.audra.graphql.utils.SortUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class PlaybookService {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final List<PlaybookSort> DEFAULT_SORT = List.of(PlaybookSort.updatedAt_DESC);
    private PlaybookRepository playbookRepository;

    public PlaybookEntity findPlaybookByUuid(UUID uuid) {
        return playbookRepository.findPlaybookByUuid(uuid).orElseThrow(
                () -> new DataNotFoundException("Playbook not available for given UUID : " + uuid.toString())
        );
    }

    public Page<PlaybookEntity> findAll(PlaybooksInput input) {
        var spec = PlaybookSpecification.byPlaybookInput(input.getWhere(), input.getSort());
        var itemsPerPage = Optional.ofNullable(input)
                .map(PlaybooksInput::getPagination).map(PageInput::getItemsPerPage).orElse(DEFAULT_PAGE_SIZE);
        var pageNumber = Optional.ofNullable(input)
                .map(PlaybooksInput::getPagination).map(PageInput::getPageNumber).orElse(DEFAULT_PAGE_NUMBER);
        var sortValues = Optional.ofNullable(input)
                .map(PlaybooksInput::getSort).filter(e -> !e.isEmpty()).orElse(DEFAULT_SORT);
        var sort = sortValues.stream()
                .map(e -> SortUtils.parse(e.name())).reduce(Sort::and).get();
        var pageable = PageRequest.of(pageNumber, itemsPerPage, sort);
        return playbookRepository.findAll(spec, pageable);
    }

    public PlaybookEntity save(PlaybookEntity entity) {
        return playbookRepository.save(entity);
    }

    public PlaybookEntity deletePlaybook(PlaybookEntity entity) {
        playbookRepository.delete(entity);
        return entity;
    }

}
