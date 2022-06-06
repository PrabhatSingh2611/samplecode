package digital.windmill.audra.feed.graphql.type;

import java.time.ZonedDateTime;
import java.util.List;

import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.FeedItem;
import digital.windmill.audra.graphql.type.Resource;
import lombok.Data;

@Data
public class Post extends FeedItem {

    private String text;
    private List<Resource> images;
    private Employee author;
    private ZonedDateTime createdAt;

}
