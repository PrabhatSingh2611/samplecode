package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.dao.entity.enums.LeaveRequestStatus;
import digital.windmill.audra.graphql.facade.impl.LeaveRequestFacadeImpl;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.LeaveRequest;
import digital.windmill.audra.service.LeaveRequestService;
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
class LeaveRequestFacadeTest {

    @Mock
    private LeaveRequestService leaveRequestService;

    @InjectMocks
    private LeaveRequestFacadeImpl leaveRequestFacade;

    private static final UUID TEST_UUID = UUID.fromString("0069e6ad-d356-472f-99cc-9256565a02a9");
    private static final Long ID = 1L;
    private static final String NAME = "PcwrDcz";
    private final static Instant LOCAL_DATE = Instant.now();
    private final static LeaveRequestStatus STATUS = LeaveRequestStatus.NEW;
    private static final EmployeeRole ROLE = EmployeeRole.EMPLOYEE;

    @Test
    void findLeaveRequestById() {
        when(leaveRequestService.findLeaveRequestById(any(Long.class)))
                .thenReturn(createLeaveRequest());

        var result = leaveRequestFacade.findLeaveRequestById(ID);
        assertNotNull(result);
        assertEquals(TEST_UUID, result.getId());
        assertEquals(STATUS, result.getStatus());
        assertEquals(TEST_UUID, result.getEmployee().getId());
    }

    private LeaveRequest createLeaveRequest() {
        LeaveRequest l = new LeaveRequest();
        l.setId(TEST_UUID);
        l.setRequestDate(LOCAL_DATE);
        l.setEmployee(createEmployee());
        l.setStatus(STATUS);
        l.setComment(NAME);

        return l;

    }

    private Employee createEmployee() {
        return Employee.builder()
                .id(TEST_UUID)
                .firstName(NAME)
                .lastName(NAME)
                .role(ROLE)
                .build();
    }
}