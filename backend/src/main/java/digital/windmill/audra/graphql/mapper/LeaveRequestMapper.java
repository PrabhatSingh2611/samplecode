package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.LeaveRequestEntity;
import digital.windmill.audra.graphql.type.LeaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class, EmployeeMapper.class})
public interface LeaveRequestMapper {

    /**
     * This method maps LeaveRequestEntity to LeaveRequest
     *
     * @param entity Leave Request Entity is input
     * @return a mapped LeaveRequest
     */
    @Mapping(target = "id", ignore = true)
    LeaveRequest mapLeaveRequestEntityToLeaveRequest(LeaveRequestEntity entity);

}
