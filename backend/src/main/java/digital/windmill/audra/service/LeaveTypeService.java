package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.LeaveTypeEntity;
import digital.windmill.audra.graphql.type.input.LeaveTypesInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface LeaveTypeService {

    LeaveTypeEntity findByUuid(UUID uuid);

    Page<LeaveTypeEntity> findAllLeaveTypes(LeaveTypesInput input);

}
