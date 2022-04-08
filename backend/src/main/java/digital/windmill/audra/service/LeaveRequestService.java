package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.LeaveRequestEntity;
import digital.windmill.audra.dao.repository.LeaveRequestRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LeaveRequestService {

    private LeaveRequestRepository leaveRequestRepository;

    public LeaveRequestEntity findById(Long id) {
        return leaveRequestRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Leave request with id "+ id + "not found.")
        );
    }

}
