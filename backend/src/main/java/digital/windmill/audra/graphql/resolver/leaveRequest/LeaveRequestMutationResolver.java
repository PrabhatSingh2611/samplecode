package digital.windmill.audra.graphql.resolver.leaveRequest;


import digital.windmill.audra.graphql.facade.LeaveRequestFacade;
import digital.windmill.audra.graphql.type.DeleteLeaveRequestPayload;
import digital.windmill.audra.graphql.type.LeaveRequestPayload;
import digital.windmill.audra.graphql.type.input.CreateLeaveRequestInput;
import digital.windmill.audra.graphql.type.input.DeleteLeaveRequestInput;
import digital.windmill.audra.graphql.type.input.PatchLeaveRequestInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LeaveRequestMutationResolver implements GraphQLMutationResolver {

    private LeaveRequestFacade leaveRequestFacade;

    public LeaveRequestPayload createLeaveRequest(CreateLeaveRequestInput leaveRequestInput) {
        return LeaveRequestPayload
                .builder()
                .leaveRequest(leaveRequestFacade.createLeaveRequest(leaveRequestInput))
                .build();
    }

    public LeaveRequestPayload patchLeaveRequest(PatchLeaveRequestInput input) {
        return new LeaveRequestPayload(leaveRequestFacade.patchLeaveRequest(input));
    }

    public DeleteLeaveRequestPayload deleteLeaveRequest(DeleteLeaveRequestInput input) {
        return DeleteLeaveRequestPayload
                .builder()
                .leaveRequest(leaveRequestFacade.deleteLeaveRequest(input))
                .build();
    }
}
