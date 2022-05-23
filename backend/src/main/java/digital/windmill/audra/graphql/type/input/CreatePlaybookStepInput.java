package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.dao.entity.enums.PlaybookStepType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlaybookStepInput {
    private String title;
    private PlaybookStepType type;
    private CreatePlaybookTaskListInput taskList;
    private CreatePlaybookVideoInput video;
    private CreatePlaybookResourceInput resource;

}
