package digital.windmill.audra.resolver;

import digital.windmill.audra.dao.entity.enums.LeaveRequestStatus;
import digital.windmill.audra.graphql.facade.LeaveRequestFacade;
import digital.windmill.audra.graphql.resolver.leaveRequestResolver.LeaveRequestResolver;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.LeaveRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LeaveRequestResolverTest {

    private static final UUID TEST_UUID = UUID.randomUUID();
    private static final Long ID = 1L;
    private static final String COMMENT = "PcwrDcz";
    private static final String NAME = "v965r2h";
    private static final Instant LOCAL_DATE = Instant.now();
    private static final LeaveRequestStatus STATUS = LeaveRequestStatus.NEW;
    private static final String ROLE = "6njELdS";

    @Mock
    private LeaveRequestFacade facade;

    @InjectMocks
    private LeaveRequestResolver leaveRequestResolver;

    @Test
    void shouldGetLeaveRequestById() {
        when(facade.findLeaveRequestById(any(Long.class)))
                .thenReturn(createLeaveRequest());

        var result = leaveRequestResolver.LeaveRequest(ID);

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(STATUS, result.getStatus());
        assertEquals(COMMENT, result.getComment());
        assertEquals(TEST_UUID, result.getEmployee().getUuid());
        assertEquals(NAME, result.getEmployee().getFirstName());
    }

    private LeaveRequest createLeaveRequest() {
        LeaveRequest l = new LeaveRequest();
        l.setUuid(TEST_UUID);
        l.setRequestDate(LOCAL_DATE);
        l.setEmployee(createEmployee());
        l.setStatus(STATUS);
        l.setComment(COMMENT);
        return l;
    }

    private Employee createEmployee() {
        return Employee.builder()
                .uuid(TEST_UUID)
                .firstName(NAME)
                .lastName(NAME)
                .role(ROLE)
                .build();
    }
}
