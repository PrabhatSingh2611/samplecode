package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.LeaveRequestEntity;
import digital.windmill.audra.dao.entity.LeaveTypeEntity;
import digital.windmill.audra.graphql.facade.impl.LeaveRequestFacadeImpl;
import digital.windmill.audra.graphql.mapper.LeaveRequestMapper;
import digital.windmill.audra.graphql.type.LeaveRequest;
import digital.windmill.audra.graphql.type.input.CreateLeaveRequestInput;
import digital.windmill.audra.graphql.type.input.DatePeriod;
import digital.windmill.audra.graphql.type.input.DeleteLeaveRequestInput;
import digital.windmill.audra.graphql.type.input.LeaveRequestsInput;
import digital.windmill.audra.graphql.type.input.PatchLeaveRequestInput;
import digital.windmill.audra.service.EmployeeService;
import digital.windmill.audra.service.LeaveRequestService;
import digital.windmill.audra.service.LeaveTypeService;
import digital.windmill.audra.service.impl.SecurityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LeaveRequestFacadeTest {

    private static final UUID TEST_UUID = UUID.randomUUID();
    private static final UUID LEAVE_REQUEST_UUID = UUID.randomUUID();
    private static final UUID EMPLOYEE_UUID = UUID.randomUUID();
    private static final UUID LEAVE_TYPE_UUID = UUID.randomUUID();

    @Mock
    private LeaveRequestService leaveRequestService;

    @Mock
    private LeaveTypeService leaveTypeService;

    @Mock
    private LeaveRequestMapper leaveRequestMapper;

    @Mock
    private SecurityService securityService;

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    private LeaveRequestFacadeImpl leaveRequestFacade;

    @Test
    void findLeaveRequestByUuid(@Mock LeaveRequest leaveRequest,
                                @Mock LeaveRequestEntity leaveRequestEntity) {
        when(leaveRequestService.findLeaveRequestByUuid(any(UUID.class)))
                .thenReturn(leaveRequestEntity);
        when(leaveRequestMapper.mapLeaveRequestEntityToLeaveRequest(any(LeaveRequestEntity.class))).thenReturn(leaveRequest);

        var result = leaveRequestFacade.findLeaveRequestByUuid(TEST_UUID);
        assertNotNull(result);
        assertSame(leaveRequest, result);
    }

    @Test
    void shouldGetLeaveRequests(
            @Mock LeaveRequestsInput input,
            @Mock LeaveRequest leaveRequest,
            @Mock LeaveRequestEntity leaveRequestEntity) {
        var pagedResponse = createOneItemPage(leaveRequestEntity);
        when(leaveRequestService.findAllLeaveRequests(any(LeaveRequestsInput.class)))
                .thenReturn(pagedResponse);
        when(leaveRequestMapper.mapLeaveRequestEntityToLeaveRequest(any(LeaveRequestEntity.class)))
                .thenReturn(leaveRequest);
        var result = leaveRequestFacade.getLeaveRequests(input);
        assertNotNull(result);
        assertSame(leaveRequest, result.getContent().get(0));
    }

    @Test
    void shouldCreateLeaveRequest(
            @Mock(answer = RETURNS_DEEP_STUBS) CreateLeaveRequestInput input,
            @Mock LeaveRequest leaveRequest,
            @Mock EmployeeEntity employeeEntity,
            @Mock LeaveRequestEntity leaveRequestEntity,
            @Mock LeaveTypeEntity leaveTypeEntity
    ) {
        when(securityService.getCurrentUserUuid()).thenReturn(TEST_UUID);
        when(input.getType().getId()).thenReturn(LEAVE_TYPE_UUID);
        when(leaveTypeService.findByUuid(LEAVE_TYPE_UUID)).thenReturn(leaveTypeEntity);
        when(employeeService.findEmployeeByUuid(any(UUID.class)))
                .thenReturn(employeeEntity);
        when(leaveRequestMapper.mapCreateLeaveRequestInputToLeaveRequestEntity(any(CreateLeaveRequestInput.class)))
                .thenReturn(leaveRequestEntity);
        when(leaveRequestMapper.mapLeaveRequestEntityToLeaveRequest(any(LeaveRequestEntity.class))).thenReturn(leaveRequest);
        when(leaveRequestService.createOrPatchLeaveRequest(any(LeaveRequestEntity.class))).thenReturn(leaveRequestEntity);

        var result = leaveRequestFacade.createLeaveRequest(input);
        assertNotNull(result);
        assertSame(leaveRequest, result);
    }

    @Test
    void shouldPatchLeaveRequest(@Mock(answer = RETURNS_DEEP_STUBS) PatchLeaveRequestInput input,
                                 @Mock LeaveRequest leaveRequest,
                                 @Mock EmployeeEntity employeeEntity,
                                 @Mock LeaveRequestEntity leaveRequestEntity,
                                 @Mock LeaveTypeEntity leaveTypeEntity,
                                 @Mock DatePeriod datePeriod) {
        when(input.getId()).thenReturn(LEAVE_REQUEST_UUID);
        when(input.getEmployee().getId()).thenReturn(EMPLOYEE_UUID);
        when(input.getPeriod()).thenReturn(datePeriod);
        when(input.getType().getId()).thenReturn(LEAVE_TYPE_UUID);
        when(leaveRequestService.findLeaveRequestByUuid(LEAVE_REQUEST_UUID)).thenReturn(leaveRequestEntity);
        when(employeeService.findEmployeeByUuid(EMPLOYEE_UUID))
                .thenReturn(employeeEntity);
        when(leaveTypeService.findByUuid(LEAVE_TYPE_UUID))
                .thenReturn(leaveTypeEntity);
        when(leaveRequestMapper.map(input,
                leaveRequestEntity)).thenReturn(leaveRequestEntity);
        when(leaveRequestMapper.mapLeaveRequestEntityToLeaveRequest(leaveRequestEntity))
                .thenReturn(leaveRequest);
        when(leaveRequestService.createOrPatchLeaveRequest(leaveRequestEntity)).thenReturn(leaveRequestEntity);

        var result = leaveRequestFacade.patchLeaveRequest(input);
        assertNotNull(result);
        assertSame(leaveRequest, result);
    }

    @Test
    void shouldDeleteLeaveRequest(
            @Mock DeleteLeaveRequestInput input,
            @Mock LeaveRequest leaveRequest,
            @Mock LeaveRequestEntity leaveRequestEntity) {
        when(input.getId()).thenReturn(TEST_UUID);
        when(leaveRequestService.findLeaveRequestByUuid(any(UUID.class))).thenReturn(leaveRequestEntity);
        when(leaveRequestMapper.mapLeaveRequestEntityToLeaveRequest(any(LeaveRequestEntity.class)))
                .thenReturn(leaveRequest);
        when(leaveRequestService.deleteLeaveRequest(any(LeaveRequestEntity.class)))
                .thenReturn(leaveRequestEntity);

        var result = leaveRequestFacade.deleteLeaveRequest(input);
        assertNotNull(result);
        assertSame(leaveRequest, result);

    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));

    }
}