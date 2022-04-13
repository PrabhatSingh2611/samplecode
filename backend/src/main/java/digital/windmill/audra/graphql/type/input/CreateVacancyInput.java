package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.dao.entity.enums.VacancyPriority;
import digital.windmill.audra.dao.entity.enums.VacancyStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateVacancyInput {
    private UUID position;
    private String description;
    private VacancyStatus status;
    private UUID assignTo;
    private VacancyPriority priority;
}
