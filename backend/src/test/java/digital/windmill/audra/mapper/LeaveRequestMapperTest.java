package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.LeaveRequestEntity;
import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.dao.entity.enums.LeaveRequestStatus;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.mapper.LeaveRequestMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class LeaveRequestMapperTest {

    @Mock
    private EmployeeMapper employeeMapper;
    @InjectMocks
    private LeaveRequestMapperImpl mapper;

    private static final LeaveRequestStatus STATUS = LeaveRequestStatus.NEW;
    private static final String TEXT = "EQSW";
    private static final Long ID = 620L;
    private static final Instant INSTANT_DATE = Instant.now();
    private static final String NAME = "Name";
    private static final UUID TEST_UUID = UUID.fromString("91817d8b-5851-413f-9fd5-31f64cef4692");

    @Test
    void mapLeaveRequestEntityToLeaveRequest() {
        var result = mapper.mapLeaveRequestEntityToLeaveRequest(createLeaveRequestEntity());

        assertNotNull(result);
        assertEquals(TEXT, result.getComment());
        assertEquals(STATUS, result.getStatus());
    }

    private LeaveRequestEntity createLeaveRequestEntity() {
        LeaveRequestEntity l = new LeaveRequestEntity();
        l.setId(ID);
        l.setComment(TEXT);
        l.setEmployee(createEmployeeEntity());
        l.setRequestDate(INSTANT_DATE);
        l.setStatus(STATUS);
        return l;
    }

    private EmployeeEntity createEmployeeEntity() {
        EmployeeEntity e = new EmployeeEntity();
        e.setId(1L);
        e.setFirstName(NAME);
        e.setLastName(NAME);
        e.setUuid(TEST_UUID);
        e.setRole(EmployeeRole.EMPLOYEE);
        return e;
    }

}