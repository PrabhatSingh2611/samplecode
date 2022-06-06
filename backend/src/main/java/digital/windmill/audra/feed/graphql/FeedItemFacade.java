package digital.windmill.audra.feed.graphql;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import digital.windmill.audra.feed.dao.FeedItemService;
import digital.windmill.audra.feed.graphql.mapper.FeedItemMapper;
import digital.windmill.audra.feed.graphql.type.FeedInput;
import digital.windmill.audra.graphql.type.FeedItem;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FeedItemFacade {

    private final FeedItemService service;
    private final FeedItemMapper mapper;

    @Transactional(readOnly = true)
    public Page<FeedItem> findItems(FeedInput input) {
        return service.findItems(input)
                .map(mapper::map);
    }

}
