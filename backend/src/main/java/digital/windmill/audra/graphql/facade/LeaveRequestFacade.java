package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.LeaveRequest;
import digital.windmill.audra.graphql.type.input.CreateLeaveRequestInput;
import digital.windmill.audra.graphql.type.input.DeleteLeaveRequestInput;
import digital.windmill.audra.graphql.type.input.LeaveRequestsInput;
import digital.windmill.audra.graphql.type.input.PatchLeaveRequestInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface LeaveRequestFacade {

    LeaveRequest findLeaveRequestByUuid(UUID uuid);

    Page<LeaveRequest> getLeaveRequests(LeaveRequestsInput input);

    LeaveRequest createLeaveRequest(CreateLeaveRequestInput input);

    LeaveRequest patchLeaveRequest(PatchLeaveRequestInput input);

    LeaveRequest deleteLeaveRequest(DeleteLeaveRequestInput input);
}
