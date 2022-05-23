package digital.windmill.audra.graphql.type.input;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UpdatePlaybookTaskListInput {
    private List<PlaybookTaskInput> tasks;
}
