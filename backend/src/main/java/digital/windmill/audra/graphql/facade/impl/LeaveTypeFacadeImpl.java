package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.LeaveTypeFacade;
import digital.windmill.audra.graphql.mapper.LeaveTypeMapper;
import digital.windmill.audra.graphql.type.LeaveType;
import digital.windmill.audra.graphql.type.input.LeaveTypesInput;
import digital.windmill.audra.service.LeaveTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class LeaveTypeFacadeImpl implements LeaveTypeFacade {

    private LeaveTypeService leaveTypeService;
    private LeaveTypeMapper leaveTypeMapper;

    @Transactional(readOnly = true)
    public LeaveType findByUuid(UUID uuid) {
        return leaveTypeMapper.map(
                leaveTypeService.findByUuid(uuid)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LeaveType> getLeaveTypes(LeaveTypesInput input) {
        return leaveTypeService.findAllLeaveTypes(input)
                .map(leaveTypeMapper::map);
    }
}
