package digital.windmill.audra.graphql.type.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestWhereInput {
    private NodeInput employee;
    private NodeInput approver;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
}