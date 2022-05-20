package digital.windmill.audra.graphql.resolver.leaveType;

import digital.windmill.audra.graphql.facade.LeaveTypeFacade;
import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.LeaveType;
import digital.windmill.audra.graphql.type.input.LeaveTypesInput;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LeaveTypeResolver implements GraphQLQueryResolver {

    private LeaveTypeFacade leaveTypeFacade;

    public ConnectionPayload<LeaveType> leaveTypes(LeaveTypesInput input) {
        return ConnectionUtils.buildPayload(leaveTypeFacade.getLeaveTypes(input));
    }
}
