package digital.windmill.audra.graphql.type.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInput {
    private EmployeeWhereInput where;
    private PageInput pagination;
}
