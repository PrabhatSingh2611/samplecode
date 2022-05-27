package digital.windmill.audra.graphql.type;

import digital.windmill.audra.dao.entity.enums.LeaveRequestStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class PatchLeaveRequestInput {

    private UUID uuid;
    private LeaveRequestStatus status;
}
