package digital.windmill.audra.graphql.type;

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
public class Objective {
    private UUID uuid;
    private Employee employee;
    private String name;
    private String description;
    private String comments;
    private ZonedDateTime dueToDate;
    private ObjectiveStatus status;
}
