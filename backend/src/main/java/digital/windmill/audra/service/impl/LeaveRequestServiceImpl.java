package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.LeaveRequestSpecification;
import digital.windmill.audra.dao.entity.LeaveRequestEntity;
import digital.windmill.audra.dao.repository.LeaveRequestRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.input.LeaveRequestsInput;
import digital.windmill.audra.graphql.type.input.PageInput;
import digital.windmill.audra.service.LeaveRequestService;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;

    @Override
    public LeaveRequestEntity findLeaveRequestByUuid(UUID uuid) {
        return leaveRequestRepository.findLeaveRequestByUuid(uuid)
                .orElseThrow(() -> new DataNotFoundException("Leave request with uuid " + uuid + "not found."));
    }

    @Override
    public Page<LeaveRequestEntity> findAllLeaveRequests(LeaveRequestsInput input) {
        var specification = LeaveRequestSpecification.byLeaveRequestsInput(input.getWhere(), input.getSort());
        var itemsPerPage = Optional.of(input).map(LeaveRequestsInput::getPagination).
                map(PageInput::getItemsPerPage).orElse(PageInput.MAX_ITEMS_PER_PAGE);
        var pageNumber = Optional.of(input).map(LeaveRequestsInput::getPagination).
                map(PageInput::getPageNumber).orElse(0);
        var pageable = PageRequest.of(pageNumber, itemsPerPage);
        return leaveRequestRepository.findAll(specification, pageable);
    }

    @Override
    public LeaveRequestEntity createOrPatchLeaveRequest(LeaveRequestEntity leaveRequestEntity) {
        return leaveRequestRepository.save(leaveRequestEntity);
    }

    @Override
    public LeaveRequestEntity deleteLeaveRequest(LeaveRequestEntity leaveRequest) {
        leaveRequestRepository.delete(leaveRequest);
        return leaveRequest;
    }
}
