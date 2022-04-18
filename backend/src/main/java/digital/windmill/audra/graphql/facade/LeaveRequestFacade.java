package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.LeaveRequest;

public interface LeaveRequestFacade {
    LeaveRequest findLeaveRequestById(Long id);
}
