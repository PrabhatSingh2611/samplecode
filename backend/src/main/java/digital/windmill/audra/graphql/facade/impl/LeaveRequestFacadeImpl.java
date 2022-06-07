package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.LeaveRequestFacade;
import digital.windmill.audra.graphql.mapper.LeaveRequestMapper;
import digital.windmill.audra.graphql.type.LeaveRequest;
import digital.windmill.audra.graphql.type.input.CreateLeaveRequestInput;
import digital.windmill.audra.graphql.type.input.DeleteLeaveRequestInput;
import digital.windmill.audra.graphql.type.input.LeaveRequestsInput;
import digital.windmill.audra.graphql.type.input.NodeInput;
import digital.windmill.audra.graphql.type.input.PatchLeaveRequestInput;
import digital.windmill.audra.service.EmployeeService;
import digital.windmill.audra.service.LeaveRequestService;
import digital.windmill.audra.service.LeaveTypeService;
import digital.windmill.audra.service.impl.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LeaveRequestFacadeImpl implements LeaveRequestFacade {

    private final LeaveRequestService leaveRequestService;
    private final LeaveRequestMapper leaveRequestMapper;
    private final EmployeeService employeeService;
    private final LeaveTypeService leaveTypeService;
    private final SecurityService securityService;

    @Transactional(readOnly = true)
    public LeaveRequest findLeaveRequestByUuid(UUID uuid) {
        return leaveRequestMapper.mapLeaveRequestEntityToLeaveRequest(
                leaveRequestService.findLeaveRequestByUuid(uuid));
    }

    @Transactional(readOnly = true)
    public Page<LeaveRequest> getLeaveRequests(LeaveRequestsInput input) {
        return leaveRequestService.findAllLeaveRequests(input)
                .map(leaveRequestMapper::mapLeaveRequestEntityToLeaveRequest);
    }

    @Transactional
    public LeaveRequest createLeaveRequest(CreateLeaveRequestInput input) {
        var mappedLeaveRequestEntity = leaveRequestMapper
                .mapCreateLeaveRequestInputToLeaveRequestEntity(input);

        Optional.ofNullable(input)
                .map(CreateLeaveRequestInput::getType)
                .map(NodeInput::getId)
                .map(leaveTypeService::findByUuid)
                .ifPresent(mappedLeaveRequestEntity::setType);
        Optional.ofNullable(securityService.getCurrentUserUuid())
                .map(employeeService::findEmployeeByUuid)
                .ifPresent(mappedLeaveRequestEntity::setEmployee);
        return leaveRequestMapper
                .mapLeaveRequestEntityToLeaveRequest(leaveRequestService.createOrPatchLeaveRequest(mappedLeaveRequestEntity));
    }

    @Transactional
    public LeaveRequest patchLeaveRequest(PatchLeaveRequestInput input) {
        var leaveRequest = leaveRequestService.findLeaveRequestByUuid(input.getId());
        Optional.ofNullable(input)
                .map(PatchLeaveRequestInput::getEmployee)
                .map(NodeInput::getId)
                .map(employeeService::findEmployeeByUuid)
                .ifPresent(leaveRequest::setEmployee);
        Optional.ofNullable(input)
                .map(PatchLeaveRequestInput::getType)
                .map(NodeInput::getId)
                .map(leaveTypeService::findByUuid)
                .ifPresent(leaveRequest::setType);
        var mappedLeaveRequestEntity = leaveRequestMapper
                .map(input, leaveRequest);
        return leaveRequestMapper
                .mapLeaveRequestEntityToLeaveRequest(leaveRequestService.createOrPatchLeaveRequest(mappedLeaveRequestEntity));
    }

    @Transactional
    public LeaveRequest deleteLeaveRequest(DeleteLeaveRequestInput input) {
        var leaveRequest = leaveRequestService.findLeaveRequestByUuid(input.getId());
        return leaveRequestMapper.mapLeaveRequestEntityToLeaveRequest(
                leaveRequestService.deleteLeaveRequest(leaveRequest)
        );
    }

}
