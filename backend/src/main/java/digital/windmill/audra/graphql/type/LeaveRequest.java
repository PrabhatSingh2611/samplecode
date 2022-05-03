package digital.windmill.audra.graphql.type;

import digital.windmill.audra.dao.entity.enums.LeaveRequestStatus;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class LeaveRequest implements Node {

    private UUID uuid;
    private Instant requestDate;
    private Employee employee;
    private LeaveRequestStatus status;
    private String comment;

}
