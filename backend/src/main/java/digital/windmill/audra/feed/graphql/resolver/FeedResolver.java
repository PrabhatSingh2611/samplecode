package digital.windmill.audra.feed.graphql.resolver;

import org.springframework.stereotype.Component;

import digital.windmill.audra.feed.graphql.FeedItemFacade;
import digital.windmill.audra.feed.graphql.type.FeedInput;
import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.FeedItem;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FeedResolver implements GraphQLQueryResolver {

    private final FeedItemFacade facade;

    public ConnectionPayload<FeedItem> feed(FeedInput input) {
        return ConnectionUtils.buildPayload(facade.findItems(input));
    }

}
