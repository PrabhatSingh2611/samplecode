package digital.windmill.audra.feed.graphql.type;

import java.util.List;

import digital.windmill.audra.graphql.type.input.PageInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedInput {

    private PageInput pagination;
    private List<FeedSort> sort;

}
