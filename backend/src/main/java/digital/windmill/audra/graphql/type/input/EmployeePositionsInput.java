package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.graphql.type.enums.EmployeePositionsSort;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePositionsInput {
    private PageInput pagination;
    private List<EmployeePositionsSort> sort;
}
