package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.LeaveType;
import digital.windmill.audra.graphql.type.input.LeaveTypesInput;

import org.springframework.data.domain.Page;

import java.util.UUID;

public interface LeaveTypeFacade {

    LeaveType findByUuid(UUID uuid);

    Page<LeaveType> getLeaveTypes(LeaveTypesInput input);
}
