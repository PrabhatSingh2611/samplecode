package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.LeaveRequestEntity;
import digital.windmill.audra.dao.repository.LeaveRequestRepository;

public class LeaveRequestService {

    private LeaveRequestRepository leaveRequestRepository;

    public LeaveRequestEntity findById(Long id) {
        return leaveRequestRepository.findById(id).orElse(null);
    }

}
