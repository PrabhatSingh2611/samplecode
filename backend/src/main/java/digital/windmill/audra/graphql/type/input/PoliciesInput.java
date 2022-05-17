package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.dao.entity.enums.PolicySort;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PoliciesInput {
    private PoliciesWhereInput where;
    private List<PolicySort> sort;
    private PageInput pagination;
}
