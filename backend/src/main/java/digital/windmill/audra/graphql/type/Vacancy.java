package digital.windmill.audra.graphql.type;

import digital.windmill.audra.dao.entity.enums.VacancyPriority;
import digital.windmill.audra.dao.entity.enums.VacancyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vacancy implements Node {

    private UUID uuid;
    private EmployeePosition position;
    private String description;
    private VacancyStatus status;
    private Employee assignTo;
    private VacancyPriority priority;
}
