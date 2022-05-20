package digital.windmill.audra.graphql.type;

import java.util.UUID;

import digital.windmill.audra.dao.entity.enums.LeaveRequestStatus;
import lombok.Data;

@Data
public class PatchLeaveRequestInput {

    private UUID uuid;
    private LeaveRequestStatus status;
}
