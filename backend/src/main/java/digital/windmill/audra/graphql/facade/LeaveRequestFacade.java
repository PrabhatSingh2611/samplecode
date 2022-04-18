package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.LeaveRequest;

public interface LeaveRequestFacade {
    /** this method takes value and return LeaveRequest
     * @param id takes Long ID as input
     * @return a specific LeaveRequest
     */
    LeaveRequest findLeaveRequestById(Long id);
}
