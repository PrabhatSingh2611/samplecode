package digital.windmill.audra.feed.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import digital.windmill.audra.feed.dao.entity.FeedItemEntity;
import digital.windmill.audra.feed.dao.repository.FeedItemRepository;
import digital.windmill.audra.feed.dao.specification.FeedItemSpecification;
import digital.windmill.audra.feed.graphql.type.FeedInput;
import digital.windmill.audra.graphql.type.input.PageInput;
import lombok.AllArgsConstructor;

@Component
@Transactional(propagation = Propagation.MANDATORY)
@AllArgsConstructor
public class FeedItemService {
    
    private final FeedItemRepository repository;
    
    public Page<FeedItemEntity> findItems(FeedInput input) {
        Specification<FeedItemEntity> specification = FeedItemSpecification.sorted(input.getSort());
        PageRequest page = Optional.of(input)
            .map(FeedInput::getPagination)
            .map(PageInput::toPageRequest)
            .orElse(PageInput.DEFAULT_PAGINATION);

        return repository.findAll(specification, page);
    }

}
