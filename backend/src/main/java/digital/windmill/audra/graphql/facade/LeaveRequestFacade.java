package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.mapper.LeaveRequestMapper;
import digital.windmill.audra.graphql.type.LeaveRequest;
import digital.windmill.audra.service.LeaveRequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LeaveRequestFacade {

    private LeaveRequestService leaveRequestService;
    private LeaveRequestMapper leaveRequestMapper;

    public LeaveRequest findLeaveRequestById(Long id) {
        return leaveRequestMapper.map(leaveRequestService.findById(id));
    }

}
