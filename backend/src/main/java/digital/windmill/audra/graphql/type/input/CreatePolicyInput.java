package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.dao.entity.enums.PolicyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePolicyInput {
    private String title;
    private ResourceInput file;
    private PolicyStatus status;
}
