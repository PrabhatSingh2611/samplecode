package digital.windmill.audra.graphql.type.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestsInput {
    private LeaveRequestWhereInput where;
    private PageInput pagination;
    private List<LeaveRequestsSortEnum> sort;
}
