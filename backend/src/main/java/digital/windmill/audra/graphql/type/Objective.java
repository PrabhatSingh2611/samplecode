package digital.windmill.audra.graphql.type;

import digital.windmill.audra.dao.entity.enums.ObjectiveStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Objective implements Node{
    private UUID id;
    private Employee employee;
    private String name;
    private String description;
    private String comments;
    private ZonedDateTime dueToDate;
    private ObjectiveStatus status;
}
