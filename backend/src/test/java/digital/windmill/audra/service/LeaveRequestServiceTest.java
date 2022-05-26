package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.LeaveRequestEntity;
import digital.windmill.audra.dao.repository.LeaveRequestRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.exception.InvalidDataInputException;
import digital.windmill.audra.graphql.type.input.LeaveRequestWhereInput;
import digital.windmill.audra.graphql.type.input.LeaveRequestsInput;
import digital.windmill.audra.service.impl.LeaveRequestServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LeaveRequestServiceTest {

    private static final UUID TEST_UUID = UUID.randomUUID();
    private final static ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.now();
    @Mock
    private LeaveRequestRepository leaveRequestRepository;

    @InjectMocks
    private LeaveRequestServiceImpl leaveRequestService;


    @Test
    void shouldFindByIdMapped(@Mock LeaveRequestEntity leaveRequestEntity) {
        when(leaveRequestRepository.findLeaveRequestByUuid(any(UUID.class))).thenReturn(Optional.of(leaveRequestEntity));
        var result = leaveRequestService.findLeaveRequestByUuid(TEST_UUID);

        assertNotNull(result);
        assertSame(leaveRequestEntity, result);
    }

    @Test
    void shouldThrowDataNotFoundException() {
        Assertions.assertThrows(DataNotFoundException.class, () -> leaveRequestService.findLeaveRequestByUuid(TEST_UUID));
    }

    @Test
    void shouldReturnAllLeaveRequests(@Mock LeaveRequestsInput input,
                                      @Mock LeaveRequestEntity announcementEntity) {
        var leaveRequestEntityPage = createOneItemPage(announcementEntity);
        when(input.getWhere()).thenReturn(LeaveRequestWhereInput
                .builder()
                .startDate(ZONED_DATE_TIME)
                .endDate(ZONED_DATE_TIME)
                .build());
        when(leaveRequestRepository.findAll((Specification<LeaveRequestEntity>) any(), any(PageRequest.class)))
                .thenReturn(leaveRequestEntityPage);
        var result = leaveRequestService.findAllLeaveRequests(input);
        assertNotNull(result);
        assertSame(leaveRequestEntityPage, result);
    }

    @Test
    void shouldSaveOrUpdateLeaveRequest(@Mock LeaveRequestEntity leaveRequestEntity) {
        when(leaveRequestEntity.getStartDate()).thenReturn(Instant.now());
        when(leaveRequestEntity.getEndDate()).thenReturn(Instant.now().plusSeconds(1));
        when(leaveRequestRepository.save(any(LeaveRequestEntity.class))).thenReturn(leaveRequestEntity);
        var result = leaveRequestService.createOrPatchLeaveRequest(leaveRequestEntity);
        assertNotNull(result);
        assertSame(leaveRequestEntity, result);
    }

    @Test
    void shouldNowSaveOrUpdateLeaveRequestWithWrongPeriod(@Mock LeaveRequestEntity leaveRequestEntity) {
        when(leaveRequestEntity.getStartDate()).thenReturn(Instant.now().plusSeconds(1));
        when(leaveRequestEntity.getEndDate()).thenReturn(Instant.now());
        InvalidDataInputException invalidDataException = assertThrows(InvalidDataInputException.class, () -> leaveRequestService.createOrPatchLeaveRequest(leaveRequestEntity));
        assertEquals("Wrong dates", invalidDataException.getMessage());
        verifyNoInteractions(leaveRequestRepository);
    }

    @Test
    void shouldDeleteAnnouncement(@Mock LeaveRequestEntity leaveRequestEntity) {
        var result = leaveRequestService.deleteLeaveRequest(leaveRequestEntity);
        assertNotNull(result);
        assertSame(leaveRequestEntity, result);
    }

    private <T> Page<T> createOneItemPage(T item) {

        return new PageImpl<>(List.of(item));

    }
}