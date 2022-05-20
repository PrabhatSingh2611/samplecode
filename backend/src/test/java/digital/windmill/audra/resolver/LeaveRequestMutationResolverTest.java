package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.LeaveRequestFacade;
import digital.windmill.audra.graphql.resolver.leaveRequest.LeaveRequestMutationResolver;
import digital.windmill.audra.graphql.type.LeaveRequest;
import digital.windmill.audra.graphql.type.input.CreateLeaveRequestInput;
import digital.windmill.audra.graphql.type.input.DeleteLeaveRequestInput;
import digital.windmill.audra.graphql.type.input.PatchLeaveRequestInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LeaveRequestMutationResolverTest {

    @Mock
    private LeaveRequestFacade facade;

    @InjectMocks
    private LeaveRequestMutationResolver resolver;

    @Test
    void shouldCreateLeaveRequest(
            @Mock CreateLeaveRequestInput input,
            @Mock LeaveRequest leaveRequest) {
        when(facade.createLeaveRequest(any(CreateLeaveRequestInput.class)))
                .thenReturn(leaveRequest);
        var result = resolver.createLeaveRequest(input);
        assertNotNull(result);
        assertSame(leaveRequest, result.getLeaveRequest());
    }

    @Test
    void shouldUpdateLeaveRequest(
            @Mock PatchLeaveRequestInput input,
            @Mock LeaveRequest leaveRequest) {
        when(facade.patchLeaveRequest(any(PatchLeaveRequestInput.class)))
                .thenReturn(leaveRequest);
        var result = resolver.patchLeaveRequest(input);
        assertNotNull(result);
        assertSame(leaveRequest, result.getLeaveRequest());
    }

    @Test
    void shouldDeleteLeaveRequest(
            @Mock DeleteLeaveRequestInput input,
            @Mock LeaveRequest leaveRequest) {
        when(facade.deleteLeaveRequest(any(DeleteLeaveRequestInput.class)))
                .thenReturn(leaveRequest);
        var result = resolver.deleteLeaveRequest(input);
        assertNotNull(result);
        assertSame(leaveRequest, result.getLeaveRequest());

    }
}

