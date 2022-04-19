package digital.windmill.audra.service;

import digital.windmill.audra.graphql.type.LeaveRequest;

public interface LeaveRequestService {

    /**This method search Leave Request and returns a mapped LeaveRequest
     * @param id it is value used to be search
     * @return a specific Leave Request
     */
    LeaveRequest findLeaveRequestById(Long id);
}
