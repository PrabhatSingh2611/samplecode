package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LeaveRequestEntity;
import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.dao.entity.enums.LeaveRequestStatus;
import digital.windmill.audra.dao.repository.LeaveRequestRepository;
import digital.windmill.audra.graphql.mapper.LeaveRequestMapper;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.LeaveRequest;
import digital.windmill.audra.service.impl.LeaveRequestServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LeaveRequestServiceTest {

    @Mock
    private LeaveRequestRepository leaveRequestRepository;
    @Mock
    private LeaveRequestMapper leaveRequestMapper;

    @InjectMocks
    private LeaveRequestServiceImpl leaveRequestService;

    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String TEXT = "BuA1VXU";
    private static final String POSITION = "mDi";
    private static final Long ID = 1L;
    private static final LeaveRequestStatus STATUS = LeaveRequestStatus.NEW;
    private static final EmployeeRole ROLE = EmployeeRole.EMPLOYEE;
    private final static Instant INSTANT_DATE = Instant.now();
    private final static ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.now();

    @Test
    void shouldFindById() {
        when(leaveRequestRepository.findById(any(Long.class))).thenReturn(createLeaveRequestEntity());
        var result = leaveRequestService.findById(ID);

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getEmployee().getUuid());
        assertEquals(ID, result.getId());
        assertEquals(INSTANT_DATE, result.getRequestDate());
        assertEquals(TEXT, result.getComment());
        assertEquals(STATUS, result.getStatus());
    }

    @Test
    void shouldFindByIdMapped() {
        when(leaveRequestRepository.findById(any(Long.class))).thenReturn(createLeaveRequestEntity());
        when(leaveRequestMapper.mapLeaveRequestEntityToLeaveRequest(any(LeaveRequestEntity.class))).thenReturn(createLeaveRequest());
        var result = leaveRequestService.findByIdMapped(ID);

        assertNotNull(result);
        assertEquals(TEST_UUID, result.getEmployee().getUuid());
        assertEquals(INSTANT_DATE, result.getRequestDate());
        assertEquals(TEXT, result.getComment());
        assertEquals(STATUS, result.getStatus());
    }

    private LeaveRequest createLeaveRequest() {
        LeaveRequest l = new LeaveRequest();
        l.setRequestDate(INSTANT_DATE);
        l.setComment(TEXT);
        l.setStatus(STATUS);
        l.setEmployee(createEmployee());
        return l;
    }

    private Employee createEmployee() {
        return Employee.builder()
                .uuid(TEST_UUID)
                .uuid(TEST_UUID)
                .birthday(ZONED_DATE_TIME)
                .firstName(TEXT)
                .lastName(TEXT)
                .build();
    }

    private Optional<LeaveRequestEntity> createLeaveRequestEntity() {
        LeaveRequestEntity l = new LeaveRequestEntity();
        l.setId(ID);
        l.setRequestDate(INSTANT_DATE);
        l.setComment(TEXT);
        l.setStatus(STATUS);
        l.setEmployee(createEmployeeEntity());
        return Optional.of(l);
    }

    private EmployeeEntity createEmployeeEntity() {
        return EmployeeEntity.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .birthday(INSTANT_DATE)
                .firstName(TEXT)
                .lastName(TEXT)
                .position(createPositionEntity())
                .role(ROLE)
                .build();
    }

    private EmployeePositionEntity createPositionEntity() {
        return EmployeePositionEntity.builder()
                .id(ID)
                .uuid(TEST_UUID)
                .name(TEXT)
                .build();
    }
}