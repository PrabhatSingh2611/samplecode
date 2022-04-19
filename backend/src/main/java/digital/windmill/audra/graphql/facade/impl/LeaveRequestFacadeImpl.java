package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.LeaveRequestFacade;
import digital.windmill.audra.graphql.type.LeaveRequest;
import digital.windmill.audra.service.LeaveRequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LeaveRequestFacadeImpl implements LeaveRequestFacade {

    private LeaveRequestService leaveRequestService;

    @Override public LeaveRequest findLeaveRequestById(Long id) {
        return leaveRequestService.findLeaveRequestById(id);
    }

}
