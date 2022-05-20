package digital.windmill.audra.graphql.type;

import java.time.ZonedDateTime;
import java.util.UUID;

import digital.windmill.audra.dao.entity.enums.LeaveRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LeaveRequest implements Node {
    private UUID id;
    private ZonedDateTime requestDate;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private Employee employee;
    private LeaveRequestStatus status;
    private String comment;
    private LeaveType type;
    private String name;
    private Long numberOfDays;
}
