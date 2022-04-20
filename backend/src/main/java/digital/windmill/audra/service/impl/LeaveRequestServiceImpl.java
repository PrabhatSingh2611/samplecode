package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.repository.LeaveRequestRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.LeaveRequestMapper;
import digital.windmill.audra.graphql.type.LeaveRequest;
import digital.windmill.audra.service.LeaveRequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private LeaveRequestRepository leaveRequestRepository;
    private LeaveRequestMapper leaveRequestMapper;

    @Override
    public LeaveRequest findLeaveRequestById(Long id) {
        var leaveRequestEntity = leaveRequestRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Leave request with id " + id + "not found."));
        return leaveRequestMapper.mapLeaveRequestEntityToLeaveRequest(leaveRequestEntity);
    }

}
