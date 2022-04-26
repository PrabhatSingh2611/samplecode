package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.dao.entity.enums.ObjectiveStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateObjectiveInput {
    private UUID employee;
    private String name;
    private String description;
    private String comments;
    private ZonedDateTime dueToDate;
    private ObjectiveStatus status;
}
