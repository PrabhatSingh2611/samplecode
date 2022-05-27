package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.LeaveTypeSpecification;
import digital.windmill.audra.dao.entity.LeaveTypeEntity;
import digital.windmill.audra.dao.repository.LeaveTypeRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.input.LeaveTypesInput;
import digital.windmill.audra.graphql.type.input.PageInput;
import digital.windmill.audra.service.LeaveTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class LeaveTypeServiceImpl implements LeaveTypeService {

    private LeaveTypeRepository leaveTypeRepository;

    @Override
    public LeaveTypeEntity findByUuid(UUID uuid) {
        return leaveTypeRepository.findByUuid(uuid).orElseThrow(
                () -> new DataNotFoundException("LeaveType not available for given UUID : " + uuid.toString())
        );
    }

    @Override
    public Page<LeaveTypeEntity> findAllLeaveTypes(LeaveTypesInput input) {
        Specification<LeaveTypeEntity> specification = LeaveTypeSpecification.leaveTypes(input.getWhere(), input.getSort());
        PageRequest pagination = Optional.ofNullable(input)
                .map(LeaveTypesInput::getPagination)
                .map(PageInput::toPageRequest)
                .orElse(PageInput.DEFAULT_PAGINATION);
        return leaveTypeRepository.findAll(specification, pagination);  
    }
}
