package digital.windmill.audra.feed.graphql.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import digital.windmill.audra.feed.dao.entity.FeedItemEntity;
import digital.windmill.audra.feed.dao.entity.PostEntity;
import digital.windmill.audra.graphql.type.FeedItem;

@Mapper(componentModel = "spring", uses = {PostMapper.class})
public abstract class FeedItemMapper {

    @Autowired
    private PostMapper postMapper;

    public FeedItem map(FeedItemEntity itm) {
        if (itm == null) {
            return null;
        }
        if (itm instanceof PostEntity post) {
            return postMapper.map(post);
        }
        throw new UnsupportedOperationException("Unsupported mapping from " + itm);
    }

}
