package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.dao.entity.enums.LeaveTypesSortEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaveTypesInput {
    private LeaveTypesWhereInput where;
    private PageInput pagination;
    private List<LeaveTypesSortEnum> sort;
}
