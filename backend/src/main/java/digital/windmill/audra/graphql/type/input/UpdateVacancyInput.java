package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.dao.entity.enums.VacancyPriority;
import digital.windmill.audra.dao.entity.enums.VacancyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateVacancyInput {
    private UUID id;
    private UUID position;
    private String description;
    private VacancyStatus status;
    private UUID assignTo;
    private VacancyPriority priority;
}
