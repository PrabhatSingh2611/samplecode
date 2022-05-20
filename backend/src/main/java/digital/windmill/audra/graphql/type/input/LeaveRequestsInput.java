package digital.windmill.audra.graphql.type.input;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestsInput {
    private LeaveRequestWhereInput where;
    private PageInput pagination;
    private List<LeaveRequestsSortEnum> sort;
}
