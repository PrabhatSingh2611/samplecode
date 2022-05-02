package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.dao.entity.enums.ObjectiveStatus;
import digital.windmill.audra.dao.entity.enums.VacancyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeObjectivesWhereInput {
    private NodeInput employee;
    private List<ObjectiveStatus> status;
}
