package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.LeaveRequestEntity;
import digital.windmill.audra.graphql.type.input.LeaveRequestsInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface LeaveRequestService {

    /**
     * This method search Leave Request by it's uuid
     *
     * @param uuid it is value used to be search
     * @return a specific Leave Request
     */
    LeaveRequestEntity findLeaveRequestByUuid(UUID uuid);

    /**
     * This method will return a list of Leave requests.
     *
     * @param input for query result
     * @return a list of Leave requests including pagination
     */
    Page<LeaveRequestEntity> findAllLeaveRequests(LeaveRequestsInput input);

    /**
     * This method will create a Leave Request by a specific value.
     *
     * @param leaveRequestEntity leaveRequestEntity of which leave request we should create
     * @return created LeaveRequestEntity
     */
    LeaveRequestEntity createOrPatchLeaveRequest(LeaveRequestEntity leaveRequestEntity);

    /**
     * This method will delete a Leave Request by a specific value.
     *
     * @param leaveRequest leaveRequest of which we should be deleting
     * @return deleted LeaveRequestEntity
     */
    LeaveRequestEntity deleteLeaveRequest(LeaveRequestEntity leaveRequest);
}
