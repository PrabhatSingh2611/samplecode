package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.dao.entity.enums.LeaveRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateLeaveRequestInput {

    private String name;
    private Long numberOfDays;
    private NodeInput employee;
    private LeaveRequestStatus status;
    private String comment;
    private NodeInput type;
    private DatePeriod period;
}
