package digital.windmill.audra.graphql.resolver.leaveRequest;

import digital.windmill.audra.graphql.facade.LeaveRequestFacade;
import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.LeaveRequest;
import digital.windmill.audra.graphql.type.LeaveRequestPayload;
import digital.windmill.audra.graphql.type.input.LeaveRequestInput;
import digital.windmill.audra.graphql.type.input.LeaveRequestsInput;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LeaveRequestResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    public LeaveRequestFacade facade;

    public LeaveRequestPayload leaveRequest(LeaveRequestInput input) {
        return LeaveRequestPayload
                .builder()
                .leaveRequest(facade.findLeaveRequestByUuid(input.getId()))
                .build();
    }

    public ConnectionPayload<LeaveRequest> leaveRequests(LeaveRequestsInput input) {
        return ConnectionUtils.buildPayload(facade.getLeaveRequests(input));
    }
}
