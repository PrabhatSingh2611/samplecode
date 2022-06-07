package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.LeaveRequestEntity;
import digital.windmill.audra.graphql.type.LeaveRequest;
import digital.windmill.audra.graphql.type.input.CreateLeaveRequestInput;
import digital.windmill.audra.graphql.type.input.PatchLeaveRequestInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class, EmployeeMapper.class, LeaveTypeMapper.class}, imports = {UUID.class})
public interface LeaveRequestMapper {

    @Mapping(target = "id", source = "uuid")
    LeaveRequest mapLeaveRequestEntityToLeaveRequest(LeaveRequestEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
    @Mapping(target = "status", source = "input.status")
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "startDate", source = "period.startDate")
    @Mapping(target = "endDate", source = "period.endDate")
    @Mapping(target = "comment", source = "input.comment")
    LeaveRequestEntity mapCreateLeaveRequestInputToLeaveRequestEntity(CreateLeaveRequestInput input);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "status", source = "input.status", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "comment", source = "input.comment", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "startDate", source = "input.period.startDate", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "endDate", source = "input.period.endDate", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LeaveRequestEntity map(PatchLeaveRequestInput input,
                           @MappingTarget LeaveRequestEntity leaveRequestEntity);

}
