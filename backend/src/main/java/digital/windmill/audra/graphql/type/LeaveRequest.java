package digital.windmill.audra.graphql.type;

import digital.windmill.audra.dao.entity.enums.LeaveRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

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
    private Long numberOfDays;

    public Long getNumberOfDays() {
        return numberOfDays == null ? calcRequestDuration()
                : numberOfDays;
    }

    private Long calcRequestDuration() {
        if (startDate == null
                || endDate == null
                || startDate.isAfter(endDate)) {
            return 0L;
        }
        return ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }
}
