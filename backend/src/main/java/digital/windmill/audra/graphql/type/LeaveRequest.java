package digital.windmill.audra.graphql.type;

import digital.windmill.audra.dao.entity.enums.LeaveRequestStatus;

import java.time.Instant;
import java.util.UUID;

public class LeaveRequest {

    private UUID uuid;
    private Instant requestDate;
    private Employee employee;
    private LeaveRequestStatus status;
    private String comment;

}
