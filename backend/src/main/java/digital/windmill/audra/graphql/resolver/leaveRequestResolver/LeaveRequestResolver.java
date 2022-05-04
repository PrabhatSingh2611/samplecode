package digital.windmill.audra.graphql.resolver.leaveRequestResolver;

import digital.windmill.audra.graphql.facade.LeaveRequestFacade;
import digital.windmill.audra.graphql.type.LeaveRequest;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor

public class LeaveRequestResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    public LeaveRequestFacade facade;

    public LeaveRequest LeaveRequest(Long id) {
        return facade.findLeaveRequestById(id);
    }

    public LeaveRequest LeaveMutation(Long id) {
        return facade.findLeaveRequestById(id);
    }

    public List<LeaveRequest> leaveRequests() {
        return List.of();
    }
}
