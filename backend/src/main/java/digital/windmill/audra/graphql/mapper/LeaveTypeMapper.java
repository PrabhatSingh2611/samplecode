package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.LeaveTypeEntity;
import digital.windmill.audra.dao.entity.enums.EndOfYearAction;
import digital.windmill.audra.graphql.type.LeaveType;
import digital.windmill.audra.graphql.type.LeaveTypeEndOfYearAction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = { EmployeeMapper.class},imports = {UUID.class})
public interface LeaveTypeMapper {

    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "endOfYearAction", source = "action", qualifiedByName = "mapAction")
    LeaveType map(LeaveTypeEntity leaveTypeEntity);
    
    @Named("mapAction")
    default LeaveTypeEndOfYearAction map(EndOfYearAction action) {
        if (action == null) {
            return null;
        }
        return switch (action) {
            case CARRY_OVER -> LeaveTypeEndOfYearAction.MOVE;
            case RESET -> LeaveTypeEndOfYearAction.EXPIRE;
            default -> null;
        };
    }
}
