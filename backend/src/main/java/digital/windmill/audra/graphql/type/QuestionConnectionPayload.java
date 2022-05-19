package digital.windmill.audra.graphql.type;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionConnectionPayload {
    private List<Question> items;
    private PageInfo pageInfo;
    private Long totalItems;
}
