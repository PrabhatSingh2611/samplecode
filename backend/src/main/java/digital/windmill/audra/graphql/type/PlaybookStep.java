package digital.windmill.audra.graphql.type;

import digital.windmill.audra.dao.entity.enums.PlaybookStepType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaybookStep implements Node {

    private UUID id;
    private String title;
    private Resource image;
    private PlaybookStepType type;
    private PlaybookTaskList taskList;
    private PlaybookVideo video;
    private PlaybookResource resource;
}
