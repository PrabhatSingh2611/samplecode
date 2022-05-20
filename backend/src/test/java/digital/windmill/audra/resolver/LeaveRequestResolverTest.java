package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.LeaveRequestFacade;
import digital.windmill.audra.graphql.resolver.leaveRequest.LeaveRequestResolver;
import digital.windmill.audra.graphql.type.LeaveRequest;
import digital.windmill.audra.graphql.type.input.LeaveRequestInput;
import digital.windmill.audra.graphql.type.input.LeaveRequestsInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LeaveRequestResolverTest {

    private static final UUID TEST_UUID = UUID.randomUUID();
    @Mock
    private LeaveRequestFacade facade;

    @InjectMocks
    private LeaveRequestResolver leaveRequestResolver;

    @Test
    void shouldGetLeaveRequestByUuid(@Mock LeaveRequestInput input,
                                     @Mock LeaveRequest leaveRequest) {
        when(input.getId()).thenReturn(TEST_UUID);
        when(facade.findLeaveRequestByUuid(any(UUID.class)))
                .thenReturn(leaveRequest);

        var result = leaveRequestResolver.leaveRequest(input);

        assertNotNull(result);
        assertSame(leaveRequest, result.getLeaveRequest());
    }

    @Test
    void shouldGetLeaveRequests(
            @Mock LeaveRequestsInput input,
            @Mock LeaveRequest leaveRequest) {
        var pagedLeaveRequests = createOneItemPage(leaveRequest);
        when(facade.getLeaveRequests(any(LeaveRequestsInput.class))).thenReturn(pagedLeaveRequests);
        var result = leaveRequestResolver.leaveRequests(input);
        assertNotNull(result);
        assertSame(leaveRequest, result.getItems().get(0));
    }


    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));

    }

}
